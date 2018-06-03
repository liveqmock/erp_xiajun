package com.wangqin.globalshop.inventory.app.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.wangqin.globalshop.biz1.app.constants.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryInoutDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryInoutMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOnWarehouseMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.vo.InventoryAddVO;
import com.wangqin.globalshop.common.enums.InventoryRecord;
import com.wangqin.globalshop.common.enums.OrderStatus;
import com.wangqin.globalshop.common.enums.StockUpStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.shiro.ShiroUser;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.ShiroUtil;
import com.wangqin.globalshop.inventory.app.emun.InventoryType;
import com.wangqin.globalshop.inventory.app.service.IInventoryService;
import com.wangqin.globalshop.inventory.app.service.IWarehouseService;
import com.wangqin.globalshop.inventory.app.service.InventoryBookingRecordService;
import com.wangqin.globalshop.inventory.app.util.ErpOrderUtil;
import com.wangqin.globalshop.inventory.app.vo.InventoryVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.wangqin.globalshop.common.enums.InventoryRecord.OperatorType.LOCKED;

/**
 * @author biscuit
 * @data 2018/05/29
 */
@Service
public class InventoryServiceImpl implements IInventoryService {
    @Autowired
    private InventoryMapperExt inventoryMapper;
    @Autowired
    private InventoryBookingRecordService inventoryRecordService;
    @Autowired
    private InventoryOnWarehouseMapperExt inventoryOnWarehouseMapper;
    @Autowired
    private InventoryInoutDOMapper inoutDOMapper;
    @Autowired
    private MallSubOrderMapperExt mallSubOrderMapperExt;
    @Autowired
    private IWarehouseService warehouseService;
    @Autowired
    private InventoryInoutMapperExt inventoryInoutMapper;
//    private OrderInventoryOnWareHouseService inventoryOnWarehouseService;

    @Override
    public InventoryAddVO queryInvBySkuCode(String skuCode) {
        return inventoryMapper.queryInvBySkuCode(skuCode);
    }

    @Override
    public void lockVirtualInv(InventoryAddVO inventory) {
        inventoryMapper.lockVirtualInv(inventory);
    }

//    @Override
//    public InventoryDO queryInventoryBySkuId(Long itemId, Long skuId) {
//        return inventoryMapper.qu;
//    }

    @Override
    public InventoryDO queryInventoryBySkuId(String itemCode, String skuCode) {
        return inventoryMapper.getInventoryBySkuId(itemCode, skuCode);
    }

    @Override
    public void insertBatchInventory(List<InventoryAddVO> inventoryList) {
        inventoryMapper.insertBatchInventory(inventoryList);
    }

    @Override
    public InventoryDO queryInventoryBySkuCode(String skuCode) {
        return inventoryMapper.queryInventoryBySkuCode(skuCode);
    }


    @Override
    public void deleteInvBySkuCode(String skuCode) {
        inventoryMapper.deleteInvBySkuCode(skuCode);
    }

    /**
     * 清除虚拟库存
     */
    @Override
    public void updateVirtualInvByItemCode(String itemCode) {
        inventoryMapper.updateVirtualInvByItemCode(itemCode);
    }

    @Override
    public void sendInventroyOrder(MallSubOrderDO order) throws InventoryException {
        InventoryDO inventory = inventoryMapper.getInventoryBySkuId(order.getItemCode(), order.getSkuCode());

        List<InventoryBookingRecordDO> records = inventoryRecordService.queryByErpOrderId(order.getOrderNo());
        int sumSend = 0;
        for (InventoryBookingRecordDO inventoryRecord : records) {
            InventoryOnWareHouseDO inventoryArea = inventoryOnWarehouseMapper.selectById(inventoryRecord.getInventoryAreaId());
            if (inventoryArea == null) {
                throw new InventoryException(
                        String.format("sendInventroy exception : InventoryRecord[%d]  InventoryAreaId[%d] is null",
                                inventoryRecord.getId(), inventoryRecord.getInventoryAreaId()));
            }
            Long send = inventoryRecord.getBookedQuantity();
            inventoryArea.setLockedInv(inventoryArea.getLockedInv() - send);
            inventoryArea.setInventory(inventoryArea.getInventory() - send);

            if (inventoryArea.getLockedInv() < 0 || inventoryArea.getInventory() < 0) {
                throw new InventoryException(String.format(
                        "sendInventroy exception : InventoryRecord[%d] remainLocked[%d] or remainInventory[%d] error!",
                        inventoryRecord.getId(), inventoryArea.getLockedInv(), inventoryArea.getInventory()));
            }
            sumSend += send;
            inventoryOnWarehouseMapper.updateByPrimaryKey(inventoryArea);

            // 记录出仓日志
            String userCreate = null;
            try {
                ShiroUser su = ShiroUtil.getShiroUser();
                userCreate = su.getLoginName();
            } catch (Exception e) {
                throw new ErpCommonException("没有登入");
            }
            InventoryInoutDO inventoryInout = new InventoryInoutDO();
            inventoryInout.setItemCode(inventoryRecord.getItemCode());
            inventoryInout.setSkuCode(inventoryRecord.getSkuCode());
            inventoryInout.setQuantity(inventoryRecord.getBookedQuantity());
            inventoryInout.setInventoryOnWarehouseNo(inventoryRecord.getInventoryOnWarehouseNo());
            inventoryInout.setOperatorType((byte) InoutOperatorType.SALE_OUT.getCode());
            inventoryInout.setCreator(userCreate);
            inventoryInout.setGmtCreate(new Date());
            inventoryInout.setGmtModify(new Date());
            inoutDOMapper.insert(inventoryInout);
        }
        // 总库存的锁定和库存减掉
        inventory.setLockedInv(inventory.getLockedInv() - sumSend);
        inventory.setInv(inventory.getInv() - sumSend);
        inventory.setGmtModify(new Date());
        inventoryMapper.updateByPrimaryKey(inventory);
        if (sumSend != order.getQuantity()) {
            throw new InventoryException(String.format(
                    "sendInventroy exception : sumSend[%d] not equals orderDetail[id =%d, quantity = %d]  !", sumSend,
                    order.getId(), order.getQuantity()));
        }
    }

    @Override
    public InventoryDO queryInventoryBySkuCode(String itemCode, String skuCode) {
        return inventoryMapper.getInventoryBySkuId(itemCode,skuCode);
    }

    @Override
    public void update(InventoryDO inventory) {
        inventoryMapper.updateByPrimaryKey(inventory);

    }

    @Override
    public int releaseInventory(MallSubOrderDO order) throws InventoryException {
        // 悲观锁 锁定库存记录，防止并发
        InventoryDO inventory = inventoryMapper.getInventoryBySkuId(order.getItemCode(), order.getSkuCode());

        List<InventoryBookingRecordDO> records = inventoryRecordService.queryByErpOrderId(order.getOrderNo());
        int sumInvRelease = 0;
        int sumTransRelease = 0;
        for (InventoryBookingRecordDO inventoryRecord : records) {
            InventoryOnWareHouseDO inventoryArea = inventoryOnWarehouseMapper.selectById(inventoryRecord.getInventoryAreaId());
            if (inventoryArea == null) {
                throw new InventoryException(
                        String.format("sendInventroy exception : InventoryRecord[%d]  InventoryAreaId[%d] is null",
                                inventoryRecord.getId(), inventoryRecord.getInventoryAreaId()));
            }
            Long release = inventoryRecord.getBookedQuantity();

            if (InventoryRecord.InventoryType.INVENTORY == inventoryRecord.getInventoryType()) {
                inventoryArea.setLockedInv(inventoryArea.getLockedInv() - release);
                if (inventoryArea.getLockedInv() < 0) {
                    throw new InventoryException(String.format(
                            "sendInventroy exception : InventoryRecord[%d] remainLocked[%d] or remainInventory[%d] error!",
                            inventoryRecord.getId(), inventoryArea.getLockedInv(), inventoryArea.getInventory()));
                }
                sumInvRelease += release;
            } else {
                inventoryArea.setLockedTransInv(inventoryArea.getLockedTransInv() - release);
                if (inventoryArea.getLockedInv() < 0) {
                    throw new InventoryException(String.format(
                            "sendInventroy exception : InventoryRecord[%d] remainLocked[%d] or remainInventory[%d] error!",
                            inventoryRecord.getId(), inventoryArea.getLockedInv(), inventoryArea.getInventory()));
                }
                sumTransRelease += release;
            }

            inventoryOnWarehouseMapper.updateByPrimaryKey(inventoryArea);
            // 删除预定记录
            inventoryRecordService.delete(inventoryRecord);
        }
        // 总库存的锁定和库存减掉
        inventory.setLockedInv(inventory.getLockedInv() - sumInvRelease);
        inventory.setLockedTransInv(inventory.getLockedTransInv() - sumTransRelease);
        inventory.setGmtModify(new Date());
        inventoryMapper.updateByPrimaryKey(inventory);

        // 修改订单状态 为已释放
        order.setStockStatus(StockUpStatus.RELEASED.getCode());
        order.setWarehouseNo(null);
        order.setGmtModify(new Date());
        mallSubOrderMapperExt.updateByPrimaryKeySelective(order);

        return sumTransRelease + sumInvRelease;

    }


    @Override
    public WarehouseCollector selectWarehousesByErpOrder(MallSubOrderDO erpOrder) throws InventoryException {
        if(erpOrder.getWarehouseNo()==null){
            throw new InventoryException(
                    String.format("子订单没有备货的仓库：erporderid=[%d],erpno=[%s]", erpOrder.getId(), erpOrder.getOrderNo()));
        }
        WarehouseCollector warehouseColl = new WarehouseCollector();
        warehouseColl.setOrderNo(erpOrder.getOrderNo());
        warehouseColl.setErpOrder(erpOrder);
        warehouseColl.setQuantity(erpOrder.getQuantity());
        warehouseColl.setWarehouseNo(erpOrder.getWarehouseNo());

        // 从预定记录里面查询已经预定的量
        List<InventoryBookingRecordDO> inventoryRecords = inventoryRecordService
                .sumBookedByInventoryType(erpOrder.getOrderNo());
        if(CollectionUtils.isEmpty(inventoryRecords)){
            throw new InventoryException(
                    String.format("子订单没有备货的记录：erporderid=[%d],erpno=[%s]", erpOrder.getId(), erpOrder.getOrderNo()));
        }
        Long lastBooked = 0L;
        Long lastTransBooked = 0L;
        for(InventoryBookingRecordDO inventoryRecord:inventoryRecords){
            if(inventoryRecord.getInventoryType()== InventoryRecord.InventoryType.TRANS_INV){
                lastTransBooked = inventoryRecord.getBookedQuantity();
            } else if(inventoryRecord.getInventoryType()== InventoryRecord.InventoryType.INVENTORY){
                lastBooked = inventoryRecord.getBookedQuantity();
            }
        }
        warehouseColl.setLastBooked(lastBooked);
        warehouseColl.setLastTransBooked(lastTransBooked);

        List<InventoryOnWareHouseDO> sumInventorys = inventoryOnWarehouseMapper.sumInventoryBySkuIdGroupbyWarehouse(erpOrder.getItemCode(),
                erpOrder.getSkuCode(),warehouseColl.getWarehouseNo());

        if(CollectionUtils.isNotEmpty(sumInventorys)){
            InventoryOnWareHouseDO inventoryArea  = sumInventorys.get(0);
            if(inventoryArea.getTotalAvailableInv()>0){
                initBooked(inventoryArea, warehouseColl);
            }
        }
        return warehouseColl;
    }


    /**
     * 计算在指定仓库可以备货的库存数量
     * @param inventory
     * @param whc
     */
    private void initBooked(InventoryOnWareHouseDO inventory, WarehouseCollector whc) {

        Long needBook = Long.valueOf(whc.getQuantity()-whc.getLastBooked()-whc.getLastTransBooked());
        long availableInv = inventory.getInventory() - inventory.getLockedInv();
        if ( availableInv> 0) {
            if ((needBook - availableInv) > 0) {
                needBook = needBook - availableInv;
                whc.setBooked(availableInv);
            } else {
                whc.setBooked(needBook);
                needBook= Long.valueOf(0);
            }
        }
        if (needBook > 0) {
            if (availableInv > 0) {
                if ((needBook - availableInv) > 0) {
                    whc.setTransBooked(availableInv);
                } else {
                    whc.setTransBooked(needBook);
                }

            }

        }
    }

    private Double score(WarehouseCollector whc) {
        Integer qua = whc.getQuantity();
        //int allBooked = whc.getTransBooked()+whc.getBooked();		//在途不参与计分
        Long allBooked = whc.getBooked();
        if(allBooked==0){
            return 0d;
        }
        if(qua.equals(allBooked)){//全部备货完成返回10分
            return 10d;
        }else{//部分备货按照比例返回 (备货数量/总数) * 10
            return allBooked/(qua*0.1d);
        }
    }

    public static class WarehouseCollector {

        public static final int STOCKSTATUS_NOT_READY = StockUpStatus.INIT.getCode();// 未备货
        public static final int STOCKSTATUS_PART_READY = StockUpStatus.PART.getCode();// 部分备货
        public static final int STOCKSTATUS_FINISHED = StockUpStatus.STOCKUP.getCode();// 备货完成
        public static final int STOCKSTATUS_TRANS_STOCKUP = StockUpStatus.TRANS_STOCKUP.getCode();// 全部在途备货
        public static final int STOCKSTATUS_TRANS_PART = StockUpStatus.TRANS_PART.getCode();// 部分在途备货
        public static final int STOCKSTATUS_MIX_STOCKUP = StockUpStatus.MIX_STOCKUP.getCode();// 混合备货完成

        private String orderNo;
        private Integer stockStatus;// 备货状态 0:未备货 1:部分备货 2 备货完成
        private Long booked;// 本次预定量
        private Long transBooked;// 本次在途预定量
        private Long lastBooked; //上次预定在途（数据库）
        private Long lastTransBooked; //上次预定在途（数据库）
        private String warehouseNo;//仓库ID
        private Integer quantity;//erporder 购买数量
        private MallSubOrderDO erpOrder;
//		private Map<Long, Integer> warehouseMap;// 实际
//		private Map<Long, Integer> transWarehouseMap;// 在途

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getWarehouseNo() {
            return warehouseNo;
        }

        public void setWarehouseNo(String warehouseNo) {
            this.warehouseNo = warehouseNo;
        }

        public Integer getStockStatus() {
            return stockStatus;
        }

        public void setStockStatus(Integer stockStatus) {
            this.stockStatus = stockStatus;
        }


//		public Map<Long, Integer> getWarehouseMap() {
//			return warehouseMap;
//		}
//
//		public void setWarehouseMap(Map<Long, Integer> warehouseMap) {
//			this.warehouseMap = warehouseMap;
//		}

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }


        public Long getBooked() {
            return booked;
        }

        public void setBooked(Long booked) {
            this.booked = booked;
        }

//		public Map<Long, Integer> getTransWarehouseMap() {
//			return transWarehouseMap;
//		}
//
//		public void setTransWarehouseMap(Map<Long, Integer> transWarehouseMap) {
//			this.transWarehouseMap = transWarehouseMap;
//		}

        public Long getTransBooked() {
            return transBooked;
        }

        public void setTransBooked(Long transBooked) {
            this.transBooked = transBooked;
        }

        public MallSubOrderDO getErpOrder() {
            return erpOrder;
        }

        public void setErpOrder(MallSubOrderDO erpOrder) {
            this.erpOrder = erpOrder;
        }

        public Long getLastBooked() {
            return lastBooked;
        }

        public void setLastBooked(Long lastBooked) {
            this.lastBooked = lastBooked;
        }

        public Long getLastTransBooked() {
            return lastTransBooked;
        }

        public void setLastTransBooked(Long lastTransBooked) {
            this.lastTransBooked = lastTransBooked;
        }

    }


    public InventoryDOMapper getMapper(){
        return inventoryMapper;
    }



    @Override
    public List<WarehouseCollector> selectWarehousesByErpOrders(List<MallSubOrderDO> erpOrders) throws InventoryException  {
        if(CollectionUtils.isEmpty(erpOrders)){
            return null;
        }
        //Long:仓库ID,Long:erporderid,WarehouseCollector
        Table<String, String, WarehouseCollector> table = HashBasedTable.create();

        String companyNo = erpOrders.get(0).getCompanyNo();

        Map<String,Integer> warehouseSeqMap = warehouseService.getWarehousePropeties(companyNo);

        for (MallSubOrderDO erpOrder : erpOrders) {
            if (erpOrder.getWarehouseNo() != null) {
                throw new InventoryException(
                        String.format("包含已经备货的仓库：erporderid=[%d],erpno=[%s]", erpOrder.getId(), erpOrder.getOrderNo()));
            }
            List<InventoryOnWareHouseDO> sumInventorys = inventoryRecordService.sumInventoryBySkuIdGroupbyWarehouse(erpOrder.getItemCode(),
                    erpOrder.getSkuCode(),null);
            if (CollectionUtils.isNotEmpty(sumInventorys)) {
                for (InventoryOnWareHouseDO inventory : sumInventorys) {

                    Long totalAvaillableInv = inventory.getInventory()-inventory.getLockedInv()+inventory.getTransInv()-inventory.getLockedTransInv();

                    if (totalAvaillableInv > 0) {
                        WarehouseCollector whc = new WarehouseCollector();
                        whc.setOrderNo(erpOrder.getOrderNo());
                        whc.setWarehouseNo(String.valueOf(inventory.getWarehouseNo()));
                        whc.setQuantity(erpOrder.getQuantity());
                        whc.setErpOrder(erpOrder);
                        initBooked(inventory, whc);
                        table.put(inventory.getWarehouseNo(), whc.getOrderNo(), whc);
                    }
                }
            }
        }
        // 计算每个仓库下面的得分。
        if (!table.isEmpty()) {
            Map<String, Double> scoreMap = Maps.newHashMap();
            Set<String> rows = table.rowKeySet();
            for (String wareId : rows) {
                Map<String, WarehouseCollector> rowData = table.row(wareId);
                for (Map.Entry<String, WarehouseCollector> entry : rowData.entrySet()) {
                    WarehouseCollector whc = entry.getValue();
                    if (scoreMap.containsKey(wareId)) {
                        Double d = scoreMap.get(wareId) + score(whc);
                        scoreMap.put(wareId, d);
                    } else {
                        scoreMap.put(wareId, score(whc));
                    }
                }
            }
            String maxWareId = null;
            Double maxD = null;
            for (String wareId : scoreMap.keySet()) {
                if (maxD == null) {
                    maxWareId = wareId;
                    maxD = scoreMap.get(wareId);
                } else {
                    if (maxD < scoreMap.get(wareId)) {
                        maxD = scoreMap.get(wareId);
                        maxWareId = wareId;
                    } else if(maxD.equals(scoreMap.get(wareId)) && warehouseSeqMap.get(wareId) > warehouseSeqMap.get(maxWareId)) {
                        maxD = scoreMap.get(wareId);
                        maxWareId = wareId;
                    }
                }
            }
            if (maxWareId != null) {
                List<WarehouseCollector> wcs = Lists.newArrayList();
                Map<String, WarehouseCollector> rowData = table.row(maxWareId);
                for (Map.Entry<String, WarehouseCollector> entry : rowData.entrySet()) {
                    wcs.add(entry.getValue());
                }
                return wcs;
            }else{
                return null;
            }
        }
        return null;
    }




    @Override
    public void lockedInventroy(WarehouseCollector warehouseCollector) throws InventoryException {
        Long bookedSum = warehouseCollector.getBooked() + warehouseCollector.getTransBooked();
        if (bookedSum == 0) {
            return ;
        }
        MallSubOrderDO erpOrder =  warehouseCollector.getErpOrder();
        String itemCode = erpOrder.getItemCode();
        String skuCode =erpOrder.getSkuCode();
        // 悲观锁 锁定库存记录，防止并发
        InventoryDO inventory = inventoryMapper.queryInventoryByCode(itemCode, skuCode);
        InventoryVo inventoryVo = new InventoryVo();
        BeanUtils.copies(inventory,inventoryVo);


        Long availableInv = inventoryVo.getAvailableInv();// 可售库存
        Long availableTransInv = inventoryVo.getAvailableTransInv();  //可用在途库存
        //校验库存是否足够
        if(availableInv<warehouseCollector.getBooked()||availableTransInv<warehouseCollector.getTransBooked()){
            throw new InventoryException(String.format(
                    "data expiration exception : detailId[%d],StockStatus[%d],CalcInventory[%d],ActualInventory[%d],CalcTransInventory[%d],ActualTransInventory[%d]",
                    warehouseCollector.getErpOrder().getId(), warehouseCollector.getStockStatus(), warehouseCollector.getBooked(), availableInv,
                    inventoryVo.getAvailableTransInv(), availableTransInv));
        }
        if(warehouseCollector.getBooked()>0){
            // 记录实际锁定记录
            insertInventoryLockedRecord(erpOrder, warehouseCollector, availableInv);
        }
        if(warehouseCollector.getTransBooked()>0){
            // 记录在途库存锁定记录
            insertTransInventoryLockedRecord(erpOrder, warehouseCollector, availableTransInv);
        }
        int records = inventoryMapper.updateLockInventory(inventoryVo.getId(), warehouseCollector.getBooked(), warehouseCollector.getTransBooked());
        if (records == 0) {
            throw new InventoryException(String.format(
                    "data expiration exception : detailId[%d],StockStatus[%d],CalcInventory[%d],ActualInventory[%d],CalcTransInventory[%d],ActualTransInventory[%d]",
                    warehouseCollector.getErpOrder().getId(), warehouseCollector.getStockStatus(), warehouseCollector.getBooked(), availableInv,
                    inventoryVo.getAvailableTransInv(), availableTransInv));
        }

        ErpOrderUtil.calculateStockStatus(erpOrder, warehouseCollector.getBooked()+warehouseCollector.getLastBooked(), warehouseCollector.getLastTransBooked()+warehouseCollector.getTransBooked());
        erpOrder.setGmtModify(new Date());
        erpOrder.setWarehouseNo(warehouseCollector.getWarehouseNo());
        mallSubOrderMapperExt.updateByPrimaryKeySelective(erpOrder);//更新状态
    }

    @Override
    public void transToInventory(Long inventoryAreaId, int toTrans, String positionNo) throws InventoryException {
        InventoryOnWareHouseDO inventoryArea = inventoryOnWarehouseMapper.selectById(inventoryAreaId);
        if (inventoryArea == null) {
            throw new InventoryException(
                    String.format("transToInventory exception :   InventoryArea[%d] is not exist!", inventoryAreaId));
        }
        if(StringUtils.isEmpty(positionNo)
//                ||positionNo.equals(inventoryArea.getPositionNo())
                ){
            transToSameInventory(inventoryArea, toTrans);
        }else{
            transToOtherInventory(inventoryArea, toTrans, positionNo);
        }
    }

    @Override
    public Object lockedInventroyErpOrder(MallSubOrderDO order) {
        return null;
    }

    @Override
    public void inventoryCheckOut(String inventoryAreaId, Integer quantity) {

    }

    private void insertInventoryLockedRecord(MallSubOrderDO erpOrder, WarehouseCollector warehouseCollector, Long availableInv)
            throws InventoryException {
        String itemCode = erpOrder.getItemCode();
        String skuCode = erpOrder.getSkuCode();
        String warehouseNo = warehouseCollector.getWarehouseNo();
        Long booked = warehouseCollector.getBooked();
        List<InventoryOnWareHouseDO> areas = inventoryOnWarehouseMapper.queryInventoryAreaByWarehouse(warehouseNo, itemCode, skuCode);
        for (InventoryOnWareHouseDO inventoryArea : areas) {
            Long available = inventoryArea.getInventory()-inventoryArea.getLockedInv();;
            if (available == 0) {
                continue;
            }
            Long bookedArea = 0L;
            if (booked >= available) {
                bookedArea = available;
                booked -= available;
            } else {
                bookedArea = booked;
                booked = 0L;
            }
            inventoryArea.setLockedInv(inventoryArea.getLockedInv() + bookedArea);
            inventoryArea.setGmtModify(new Date());
            if (inventoryArea.getLockedInv() > inventoryArea.getInventory()) {
                throw new InventoryException(String.format(
                        "insertLockedRecord inventory check exception : detailId[%d],StockStatus[%d],CalcInventory[%d],ActualInventory[%d]",
                        erpOrder.getId(), warehouseCollector.getStockStatus(), booked, availableInv));
            }
            inventoryOnWarehouseMapper.updateByPrimaryKey(inventoryArea);
            inventoryRecordService
                    .insert(buildLockedRecord(erpOrder, bookedArea, inventoryArea, skuCode, InventoryType.INVENTORY));
            // 预定量为0直接推出循环
            if (booked == 0) {
                break;
            }
        }
        //计算实际锁定的数量
        if(booked>0){
            warehouseCollector.setBooked(warehouseCollector.getBooked()-booked);
        }
    }
    /**
     * 一个货架入仓到另外一个货架
     *
     */
    public void transToOtherInventory(InventoryOnWareHouseDO inventoryArea, int toTrans, String positionNo) throws InventoryException{

        if (inventoryArea.getTransInv() < toTrans) {
            throw new InventoryException(String.format("InventoryArea[%d] transInv less than toTrans[%d]",
                    inventoryArea.getTransInv(), toTrans));
        }
        Long availableInv = inventoryArea.getTransInv() - inventoryArea.getLockedTransInv();
        // 1、如果可用的在途库存不够，则先释放，直到够用为止
        if (availableInv < toTrans) {
            Long lockedCheckInv = toTrans - availableInv;// 需要释放锁定库存
            // int release = 0;
            List<InventoryBookingRecordDO> records = inventoryRecordService.sumInventoryCheckRecord(inventoryArea.getId(),
                    InventoryType.TRANS_INV, Arrays.asList(OrderStatus.INIT.getCode()/*, OrderStatus.CONFIRM.getCode()*/));
            for (InventoryBookingRecordDO inventoryRecord : records) {
                Long sumBooked = inventoryRecord.getBookedQuantity();
                lockedCheckInv -= sumBooked;
                // release += sumBooked;
                MallSubOrderDO erpOrder = mallSubOrderMapperExt.selectBySubOrderNo(inventoryRecord.getOrderNo());
                releaseInventory(erpOrder);
                if (lockedCheckInv <= 0) {
                    break;
                }
            }
            if (lockedCheckInv > 0) {
                throw new InventoryException("inventoryCheck is not enough!");
            }
            inventoryArea = inventoryOnWarehouseMapper.selectById(inventoryArea.getId());
        }


        // 2、总库存操作： 减掉在途库存 转移到 实际库存
        InventoryDO inventory = inventoryMapper.getInventoryBySkuId(inventoryArea.getItemCode(), inventoryArea.getSkuCode());
        int inventoryRecords = inventoryMapper.updateInventoryTransCheck(inventory.getId(), inventory.getTransInv(),
                toTrans);
        if (inventoryRecords <= 0) {
            throw new InventoryException("Inventory data expire exception!");
        }

        // 3、减掉原货架上的在途库存
        int inventoryAreaRecords = inventoryOnWarehouseMapper.updateInventoryAreaTransCheck(inventoryArea.getId(),
                inventoryArea.getTransInv(), toTrans);
        if (inventoryAreaRecords <= 0) {
            throw new InventoryException("inventoryArea data expire exception!");
        }

        //4、转移到新的货架的实际库存上
        InventoryOnWareHouseDO otherInventoryArea = inventoryOnWarehouseMapper.getInventoryArea(inventoryArea.getItemCode(), inventoryArea.getSkuCode(), inventoryArea.getWarehouseNo(), positionNo);
        if(otherInventoryArea==null) {
            otherInventoryArea = new InventoryOnWareHouseDO();
            otherInventoryArea.setItemCode(inventoryArea.getItemCode());
            otherInventoryArea.setItemName(inventoryArea.getItemName());
            otherInventoryArea.setUpc(inventoryArea.getUpc());
            otherInventoryArea.setScale(inventoryArea.getScale());
            otherInventoryArea.setSkuCode(inventoryArea.getSkuCode());
            otherInventoryArea.setSkuPic(inventoryArea.getSkuPic());
            otherInventoryArea.setInventory(Long.valueOf(toTrans));
            otherInventoryArea.setLockedInv(0L);
            otherInventoryArea.setTransInv(0L);
            otherInventoryArea.setLockedTransInv(0L);
//            otherInventoryArea.setWarehouseId(inventoryArea.getWarehouseId());
            otherInventoryArea.setWarehouseNo(inventory.getCompanyNo());
            otherInventoryArea.setWarehouseName(inventoryArea.getWarehouseName());
//            otherInventoryArea.setPositionNo(positionNo);
            otherInventoryArea.setGmtCreate(new Date());
            otherInventoryArea.setGmtModify(new Date());
            inventoryOnWarehouseMapper.insert(otherInventoryArea);
        } else {
            int otherInventoryAreaRecords = inventoryOnWarehouseMapper.updateInventoryAreaAddInventory(otherInventoryArea.getId(),
                    otherInventoryArea.getInventory(), toTrans);
            if (otherInventoryAreaRecords <= 0) {
                throw new InventoryException("otherInventoryAreaRecords data expire exception!");
            }
        }

        //记录库存变动日志 。FIXME ：仅仅记录了到新货架的日志，未记录来自于哪个货架
        String userCreate = null;
        try {
            ShiroUser su = ShiroUtil.getShiroUser();
            userCreate = su.getLoginName();
        } catch (Exception e) {
            throw new ErpCommonException("没有登入");
        }
        InventoryInoutDO inventoryInout = new InventoryInoutDO();
        inventoryInout.setItemCode(otherInventoryArea.getItemCode());
        inventoryInout.setSkuCode(otherInventoryArea.getSkuCode());
        inventoryInout.setSkuCode(otherInventoryArea.getSkuCode());
        inventoryInout.setQuantity((long) toTrans);
        inventoryInout.setSkuCode(otherInventoryArea.getSkuCode());
        inventoryInout.setInventoryOnWarehouseNo(otherInventoryArea.getInventoryOnWarehouseNo());
        inventoryInout.setWarehouseNo(otherInventoryArea.getWarehouseNo());
//        inventoryInout.setPositionNo(otherInventoryArea.getPositionNo());
        inventoryInout.setOperatorType((byte) InoutOperatorType.TRANS_IN.getCode());
        inventoryInout.setCreator(userCreate);
        inventoryInout.setGmtCreate(new Date());
        inventoryInout.setGmtModify(new Date());
        inventoryInoutMapper.insert(inventoryInout);
    }

    public void transToSameInventory(InventoryOnWareHouseDO inventoryArea, int toTrans) throws InventoryException {
        Long lockedTransInv = inventoryArea.getLockedTransInv();// 已锁定的在途数量
        Long transInv = inventoryArea.getTransInv();// 在途数量

        if (transInv < toTrans) {
            throw new InventoryException(
                    String.format("transToInventory exception :   InventoryArea[%d ，%d] is less than toTrans[%d]!",
                            inventoryArea.getId(), transInv, toTrans));
        }
        Long lockedTransToInv = Math.min(toTrans, lockedTransInv);// 在途锁定 ->
        // 实际库存锁定的数量
        int records = inventoryOnWarehouseMapper.updateTransToInventoryArea(inventoryArea.getId(), toTrans, lockedTransToInv,
                lockedTransInv);
        if (records == 0) {// 数据过期，直接抛异常
            throw new InventoryException(
                    String.format("transToInventory data expiration exception :InventoryArea[%d] , toTrans[%d] ",
                            inventoryArea.getId(), toTrans));
        }
        InventoryDO inventory = inventoryMapper.getInventoryBySkuId(inventoryArea.getItemCode(), inventoryArea.getSkuCode());
        // 修改inventory表
        int iRecords = inventoryMapper.updateTransToInventory(inventoryArea.getItemCode(), inventoryArea.getSkuCode(),
                toTrans, lockedTransToInv, inventory.getLockedTransInv());
        if (iRecords == 0) {// 数据过期，直接抛异常
            throw new InventoryException(String.format(
                    "transToInventory updateTransToInventory data expiration exception :Inventory[%d] , toTrans[%d] ",
                    inventory.getId(), toTrans));
        }

        // 记录入仓日志
        String userCreate = null;
        try {
            ShiroUser su = ShiroUtil.getShiroUser();
            userCreate = su.getLoginName();
        } catch (Exception e) {
            throw new ErpCommonException("没有登入");
        }
        InventoryInoutDO inventoryInout = new InventoryInoutDO();
        inventoryInout.setItemCode(inventoryArea.getItemCode());
        inventoryInout.setSkuCode(inventoryArea.getSkuCode());
        inventoryInout.setQuantity(Long.valueOf(toTrans));
        inventoryInout.setSkuCode(inventoryArea.getSkuCode());
        inventoryInout.setInventoryOnWarehouseNo(inventoryArea.getInventoryOnWarehouseNo());
//        inventoryInout.setPositionNo(inventoryArea.getPositionNo());
        inventoryInout.setOperatorType((byte) InoutOperatorType.TRANS_IN.getCode());
        inventoryInout.setCreator(userCreate);
        inventoryInout.setGmtCreate(new Date());
        inventoryInout.setGmtModify(new Date());
        inventoryInoutMapper.insertSelective(inventoryInout);

        if (lockedTransToInv > 0) {
            // 上面更新成功，变更inventoryRecord表中的在途转实际
            Map<String, Object> param = new HashMap<>();
            param.put("inventory_area_id", inventoryArea.getId());
            param.put("inventory_type", InventoryType.TRANS_INV);
            List<InventoryBookingRecordDO> inventoryRecords = inventoryRecordService.queryByOnWarehouseNoAndInventoryType(inventoryArea.getInventoryOnWarehouseNo(),InventoryType.TRANS_INV);// 需要在途转实际的记录
            Long remainToTrans = lockedTransToInv;
            // 用于存储变更过类型的订单列表，这些订单需要重新计算stockup的状态， 例如是在途备货完成的，可能需要改成备货完成
            Set<String> orderIds = new HashSet<>();

            for (InventoryBookingRecordDO inventoryRecord : inventoryRecords) {
                Long trans = inventoryRecord.getBookedQuantity();
                if (remainToTrans >= trans) {// 直接把类型转变为实际库存锁定类型
                    inventoryRecord.setInventoryType(InventoryType.INVENTORY);
                    inventoryRecordService.updateSelectiveById(inventoryRecord);
                    orderIds.add(inventoryRecord.getOrderNo());
                    remainToTrans -= trans;
                    if (remainToTrans == 0) {
                        break;
                    }
                } else {// 如果剩下的数量不够一条record的数量，则需要拆分record,例如转换5个，而recordbooked
                    // =10: [TRANS_INV,10] -> [TRANS_INV,5]+ [INVENTORY,5]
                    inventoryRecord.setBookedQuantity(trans - remainToTrans);
                    inventoryRecordService.updateSelectiveById(inventoryRecord);

                    // 剩下的新增一条InventoryType.INVENTORY的记录
                    MallSubOrderDO erpOrder = mallSubOrderMapperExt.selectBySubOrderNo(inventoryRecord.getSubOrderNo());
                    inventoryRecordService.insert(buildLockedRecord(erpOrder, remainToTrans, inventoryArea,
                            inventoryRecord.getSkuCode(), InventoryType.INVENTORY));

                    orderIds.add(inventoryRecord.getSubOrderNo());
                    remainToTrans = 0L;
                    break;
                }
            }

            if (remainToTrans > 0) {// 数据有问题
                throw new InventoryException(String.format("transToInventory exception :  remainToTrans > 0 !"));
            }

            // //重新计算备货状态
            for (String orderId : orderIds) {
                MallSubOrderDO erpOrder = mallSubOrderMapperExt.selectBySubOrderNo(orderId);

                Integer stockStatus = erpOrder.getStockStatus();

                List<InventoryBookingRecordDO> inventoryTypeBooked = inventoryRecordService
                        .sumBookedByInventoryType(erpOrder.getSubOrderNo());
                int quantity = erpOrder.getQuantity();// 订单购买量
                Long sumBooked = inventoryTypeBooked.stream().mapToLong((t) -> t.getBookedQuantity()).sum();// 已经预定的总量

                int decideStockup = decideStockup(inventoryTypeBooked);

                if (quantity == sumBooked) {// 全部预定,重新订单计算stockstatus
                    if (decideStockup == 2) {// 备货完成
                        stockStatus = WarehouseCollector.STOCKSTATUS_FINISHED;
                    } else if (decideStockup == 1) {// 混合备货完成
                        stockStatus = WarehouseCollector.STOCKSTATUS_MIX_STOCKUP;
                    } else {
                        new InventoryException(String.format(
                                "transToInventory  calc order[%d] stockStatus exception : decideStockup[%d]", orderId,
                                decideStockup));
                    }
                    erpOrder.setStockStatus(stockStatus);
                    mallSubOrderMapperExt.updateByPrimaryKeySelective(erpOrder);
                }
            }
        }
    }

    private int decideStockup(List<InventoryBookingRecordDO> inventoryRecords) throws InventoryException {

        if (inventoryRecords == null || inventoryRecords.size() == 0) {
            return 0;// 无记录
        } else if (inventoryRecords.size() == 2) {
            return 1;// 混合
        } else if (inventoryRecords.size() == 1) {
            InventoryBookingRecordDO inventoryRecord = inventoryRecords.get(0);
            if (InventoryType.INVENTORY == inventoryRecord.getInventoryType()) {
                return 2;
            } else if (InventoryType.TRANS_INV == inventoryRecord.getInventoryType()) {
                return 3;
            }
        }
        throw new InventoryException("decideStockup incorrect exception !");

    }
    private void insertTransInventoryLockedRecord(MallSubOrderDO erpOrder, WarehouseCollector warehouseCollector,
                                                  Long availableInv) throws InventoryException {
        String itemId = erpOrder.getItemCode();
        String skuId = erpOrder.getSkuCode();
        String warehouseId = warehouseCollector.getWarehouseNo();
        Long booked = warehouseCollector.getTransBooked();
        List<InventoryOnWareHouseDO> areas = inventoryOnWarehouseMapper.queryInventoryAreaByWarehouse(warehouseId, itemId, skuId);
        for (InventoryOnWareHouseDO inventoryArea : areas) {
            Long available = inventoryArea.getAvailableTransInv();
            if (available == 0) {
                continue;
            }
            Long bookedArea = 0L;
            if (booked >= available) {
                bookedArea = available;
                booked -= available;
            } else {
                bookedArea = booked;
                booked = 0L;
            }
            inventoryArea.setLockedTransInv(inventoryArea.getLockedTransInv() + bookedArea);
            inventoryArea.setGmtModify(new Date());
            if (inventoryArea.getLockedTransInv() > inventoryArea.getTransInv()) {
                throw new InventoryException(String.format(
                        "insertTransInventoryLockedRecord inventory check exception : detailId[%d],StockStatus[%d],CalcInventory[%d],ActualInventory[%d]",
                        erpOrder.getId(), warehouseCollector.getStockStatus(), booked, availableInv));
            }
            inventoryOnWarehouseMapper.updateByPrimaryKey(inventoryArea);
            inventoryRecordService
                    .insert(buildLockedRecord(erpOrder, bookedArea, inventoryArea, skuId, InventoryRecord.InventoryType.TRANS_INV));
            // 预定量为0直接推出循环
            if (booked == 0) {
                break;
            }
        }
        //计算实际锁定的数量
        if(booked>0){
            warehouseCollector.setTransBooked(warehouseCollector.getTransBooked()-booked);
        }
    }
    private InventoryBookingRecordDO buildLockedRecord(MallSubOrderDO erpOrder, Long booked, InventoryOnWareHouseDO inventory, String skuId,
                                                       String inventoryType) {
        InventoryBookingRecordDO bookedRecord = new InventoryBookingRecordDO();
        bookedRecord.setOrderNo(erpOrder.getOrderNo());
        bookedRecord.setItemCode(erpOrder.getItemCode());
        bookedRecord.setSkuCode(erpOrder.getSkuCode());
        bookedRecord.setInventory(inventory.getInventory());
        bookedRecord.setBookedQuantity(booked);
        bookedRecord.setInventoryAreaId(inventory.getId());
        bookedRecord.setInventoryOnWarehouseNo(inventory.getInventoryOnWarehouseNo());
        bookedRecord.setQuantity(Long.valueOf(erpOrder.getQuantity()));
        bookedRecord.setInventoryType(inventoryType);
        bookedRecord.setOperatorType(LOCKED);
        Date now = new Date();
        bookedRecord.setGmtCreate(now);
        bookedRecord.setGmtModify(now);
        return bookedRecord;
    }

}
