package com.wangqin.globalshop.order.app.service.mall.impl;

import com.google.common.collect.Maps;
import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.enums.OrderReturnStatus;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallReturnOrderDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.common.enums.InventoryRecord;
import com.wangqin.globalshop.common.enums.StockUpStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.inventory.OrderInventoryBookingRecordService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import com.wangqin.globalshop.order.app.util.ErpOrderUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class MallSubOrderServiceImpl implements IMallSubOrderService {
    @Autowired
    private MallSubOrderMapperExt mallSubOrderDOMapper;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private MallOrderMapperExt mallOrderMapper;
    @Autowired
    private OrderInventoryBookingRecordService inventoryRecordService;
    @Autowired
    private MallReturnOrderDOMapperExt returnOrderDOMapper;


    @Override
    public List<MallSubOrderDO> selectByOrderNo(String orderNo) {
        return mallSubOrderDOMapper.selectByOrderNo(orderNo);
    }

    @Override
    public List<MallSubOrderDO> selectBatchIds(List<Long> erpOrderIdList) {
        return mallSubOrderDOMapper.selectBatchIds(erpOrderIdList);
    }

    @Override
    public void updateBatchById(List<MallSubOrderDO> erpOrderList) {
        for (MallSubOrderDO mallSubOrderDO : erpOrderList) {
            mallSubOrderDOMapper.updateByPrimaryKeySelective(mallSubOrderDO);
        }
    }

    @Override
    public List<MallSubOrderDO> queryByShippingOrder(ShippingOrderVO shippingOrderQueryVO) {
        return mallSubOrderDOMapper.queryByShippingOrder(shippingOrderQueryVO);
    }

    @Override
    public int selectCount(MallSubOrderDO erpOrderQuery) {
        return mallSubOrderDOMapper.selectCount(erpOrderQuery);
    }

    @Override
    public void update(MallSubOrderDO order) {
        mallSubOrderDOMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void delete(MallSubOrderDO order) {
        mallSubOrderDOMapper.deleteByPrimaryKey(order.getId());
    }

    @Override
    public List<MallSubOrderDO> selectUnClosedByOrderNo(String orderNo) {
        return mallSubOrderDOMapper.selectUnClosedByOrderNo(orderNo);
    }

    @Override
    public List<MallSubOrderDO> queryErpOrders(MallSubOrderVO erpOrderQueryVO) {
        erpOrderQueryVO.init();
        return mallSubOrderDOMapper.queryErpOrders(erpOrderQueryVO);
    }

    @Override
    public MallSubOrderDO selectById(Long id) {
        return mallSubOrderDOMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void closeErpOrder(MallSubOrderDO erpOrder) throws InventoryException {
        erpOrder.setStatus(OrderStatus.CLOSE.getCode());
        erpOrder.setGmtModify(new Date());
        mallSubOrderDOMapper.updateByPrimaryKeySelective(erpOrder);

        inventoryService.release(erpOrder);
//        }
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void splitErpOrder(MallSubOrderDO erpOrder, Integer splitCount) throws InventoryException {

        MallSubOrderDO newErpOrder = new MallSubOrderDO();
        BeanUtils.copyProperties(erpOrder, newErpOrder);
        newErpOrder.setId(null);
//		newErpOrder.setErpNo(erpNo);
        newErpOrder.setStatus(OrderStatus.PAID.getCode());
        newErpOrder.setStockStatus(StockUpStatus.INIT.getCode());
        newErpOrder.setGmtModify(new Date());
        newErpOrder.setQuantity(erpOrder.getQuantity() - splitCount);
        newErpOrder.setWarehouseNo(null);
        //获取子订单数量便于产生单号
        List<MallSubOrderDO> erpOrders = mallSubOrderDOMapper.selectByOrderNo(erpOrder.getOrderNo());
        newErpOrder.setShopCode(CodeGenUtil.getShopCode());
        mallSubOrderDOMapper.insert(newErpOrder);

        erpOrder.setQuantity(splitCount);
        erpOrder.setGmtModify(new Date());
//		this.baseMapper.updateById(erpOrder);
        //未备货状态
        if (erpOrder.getStockStatus() == StockUpStatus.INIT.getCode() || erpOrder.getStockStatus() == StockUpStatus.RELEASED.getCode()) {
            mallSubOrderDOMapper.updateByPrimaryKeySelective(erpOrder);

        } else {//部分备货或者全部备货状态
            List<InventoryBookingRecordDO> records = inventoryRecordService.queryByErpOrderId(erpOrder.getOrderNo());
            Long realBooked = 0L;//现货备货数量
            Long transBooked = 0L;//在途备货数量
            Map<Long, InventoryBookingRecordDO> realMaps = Maps.newHashMap();//现货备货记录
            Map<Long, InventoryBookingRecordDO> transMaps = Maps.newHashMap();//在途备货记录
            if (CollectionUtils.isNotEmpty(records)) {
                for (InventoryBookingRecordDO record : records) {
                    if (record.getInventoryType() == InventoryRecord.InventoryType.INVENTORY) {
                        realBooked += record.getBookedQuantity();
                        realMaps.put(record.getId(), record);
                    } else {
                        transBooked += record.getBookedQuantity();
                        transMaps.put(record.getId(), record);
                    }
                }
            } else {
                throw new InventoryException("无备货记录，数据异常");
            }
            //要拆分的主订单比全部备货数量还大，原来的备货直接给予主订单，新增一条未备货的子订单即可
            if (splitCount >= (realBooked + transBooked)) {
                //无剩余备货订单
                for (InventoryBookingRecordDO record : records) {
                    record.setQuantity(Long.valueOf(erpOrder.getQuantity()));
                    record.setGmtModify(new Date());
                }
                inventoryRecordService.updates(records);
                //根据备货数量计算订单状态
                ErpOrderUtil.calculateStockStatus(erpOrder, realBooked, transBooked);
                //新订单改为未备货
                mallSubOrderDOMapper.updateByPrimaryKeySelective(erpOrder);

            } else if (splitCount > realBooked) {//比现货备货大，现货全部给予主订单。
                Long mainReal = realBooked;  //主订单现货
                Long subReal = 0L;   //子订单现货

                Long mainTrans = splitCount - realBooked;  //主订单在途
                Long subTrans = realBooked + transBooked - splitCount;  //子订单在途


                //有剩余在途备货订单
                Long x = splitCount - realBooked;
                if (!transMaps.isEmpty()) {
                    for (Long id : transMaps.keySet()) {
                        InventoryBookingRecordDO tran = transMaps.get(id);
                        if (x > 0) {
                            if (x < tran.getBookedQuantity()) {
                                //拆
                                Long tx = tran.getBookedQuantity() - x;
                                //x的数量给主订单，tx的数量给子订单
                                //新record初始化
                                InventoryBookingRecordDO newRecord = new InventoryBookingRecordDO();
                                BeanUtils.copyProperties(tran, newRecord);
                                newRecord.setId(null);
                                newRecord.setGmtCreate(new Date());
                                newRecord.setGmtModify(new Date());
                                newRecord.setBookedQuantity(tx);
                                newRecord.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
                                newRecord.setOrderNo(newErpOrder.getOrderNo());
                                inventoryRecordService.insert(newRecord);

                                //老record数据更新
                                tran.setBookedQuantity(x);
                                tran.setQuantity(Long.valueOf(erpOrder.getQuantity()));
                                tran.setGmtModify(new Date());

                                x = 0L;
                            } else {
                                //不拆，全部给主订单
                                x -= tran.getBookedQuantity();
                                tran.setQuantity(Long.valueOf(erpOrder.getQuantity()));
                                tran.setGmtModify(new Date());
                            }

                        } else {
                            //剩余在途全部给子订单
                            tran.setOrderNo(newErpOrder.getOrderNo());
                            tran.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
                            tran.setGmtModify(new Date());
                        }
                    }
                }
                //现货全部给主订单
                if (!realMaps.isEmpty()) {
                    for (Long id : realMaps.keySet()) {
                        InventoryBookingRecordDO real = realMaps.get(id);
                        real.setQuantity(Long.valueOf(erpOrder.getQuantity()));
                        real.setGmtModify(new Date());
                    }
                }
                //更新全部在途或者现货record
                inventoryRecordService.updates(records);
                ErpOrderUtil.calculateStockStatus(erpOrder, mainReal, mainTrans);
                mallSubOrderDOMapper.updateByPrimaryKeySelective(erpOrder);

                newErpOrder.setWarehouseNo(erpOrder.getWarehouseNo());
                ErpOrderUtil.calculateStockStatus(newErpOrder, subReal, subTrans);
                mallSubOrderDOMapper.updateByPrimaryKeySelective(newErpOrder);
            } else {//从1 <= splitCount <= realBooked 个
//				int mainReal = realBooked;
//				int mainTrans = 0;
                Long subReal = realBooked - splitCount; //子订单实际备货数量
                Long subTrans = transBooked;//子订单在途备货数量

                //有剩余现货备货订单
                Integer x = splitCount;
                for (Long id : realMaps.keySet()) {
                    InventoryBookingRecordDO real = realMaps.get(id);
                    if (x > 0) {
                        if (x < real.getBookedQuantity()) {
                            //拆
                            long tx = real.getBookedQuantity() - x;
                            //x的数量给主订单，tx的数量给子订单
                            //新record初始化
                            InventoryBookingRecordDO newRecord = new InventoryBookingRecordDO();
                            BeanUtils.copyProperties(real, newRecord);
                            newRecord.setId(null);
                            newRecord.setGmtCreate(new Date());
                            newRecord.setGmtModify(new Date());
                            newRecord.setBookedQuantity(tx);
                            newRecord.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
                            newRecord.setOrderNo(newErpOrder.getOrderNo());
                            inventoryRecordService.insert(newRecord);

                            //老record数据更新
                            real.setBookedQuantity(Long.valueOf(x));
                            real.setQuantity(Long.valueOf(erpOrder.getQuantity()));
                            real.setGmtModify(new Date());

                            x = 0;
                        } else {
                            //不拆，全部给主订单
                            x = x - Integer.valueOf(Math.toIntExact(real.getBookedQuantity()));
                            real.setQuantity(Long.valueOf(erpOrder.getQuantity()));
                            real.setGmtModify(new Date());
                        }
                    } else {//剩下现货都给子订单
                        real.setOrderNo(newErpOrder.getOrderNo());
                        real.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
                        real.setGmtModify(new Date());
                    }
                }
                //在途全部给子订单
                if (!transMaps.isEmpty()) {
                    for (Long id : transMaps.keySet()) {
                        InventoryBookingRecordDO tran = transMaps.get(id);
                        tran.setOrderNo(newErpOrder.getOrderNo());
                        tran.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
                        tran.setGmtModify(new Date());
                    }
                }
                erpOrder.setStockStatus(StockUpStatus.STOCKUP.getCode());
                erpOrder.setGmtModify(new Date());
                mallSubOrderDOMapper.updateByPrimaryKeySelective(erpOrder);

                newErpOrder.setWarehouseNo(erpOrder.getWarehouseNo());
                ErpOrderUtil.calculateStockStatus(newErpOrder, subReal, subTrans);
                mallSubOrderDOMapper.updateByPrimaryKeySelective(newErpOrder);
                //更新全部在途或者现货record
                inventoryRecordService.updates(records);
            }

        }
    }

//    @Override
//    public JsonResult lockErpOrder(MallSubOrderDO erpOrder) throws InventoryException {
//        if(erpOrder.getStatus()!=ORDER_SATUTS_INIT.getCode()){
//            return JsonResult.buildFailed("订单状态错误");
//        }
//        //未备货订单
//        if(erpOrder.getStockStatus()==StockUpStatus.INIT.getCode()||erpOrder.getStockStatus()==StockUpStatus.RELEASED.getCode()){
//            if(erpOrder.getWarehouseNo()==null){
//                List<MallSubOrderDO> erpOrders = Lists.newArrayList();
//                erpOrders.add(erpOrder);
//                List<OrderIInventoryServiceImpl.WarehouseCollector> wcs = inventoryService.selectWarehousesByErpOrders(erpOrders);
//                if(CollectionUtils.isNotEmpty(wcs)){
//                    inventoryService.lockedInventroy(wcs.get(0));
//                }else{
//                    return JsonResult.buildFailed("没有库存备货失败");
//                }
//            }else{
//                return JsonResult.buildFailed("没有仓库信息");
//            }
//        }else{
//            if(erpOrder.getStockStatus()!=StockUpStatus.STOCKUP.getCode()&&erpOrder.getStockStatus()!=StockUpStatus.MIX_STOCKUP.getCode()&&erpOrder.getWarehouseNo()!=null){
//                OrderIInventoryServiceImpl.WarehouseCollector wc = inventoryService.selectWarehousesByErpOrder(erpOrder);
//                if(wc!=null){
//                    inventoryService.lockedInventroy(wc);
//                }else{
//                    return JsonResult.buildFailed("没有库存备货失败");
//                }
//            }else{
//                return JsonResult.buildFailed("订单备货状态错误");
//            }
//
//        }
//        return JsonResult.buildSuccess(true);
//    }
    //导出
    @Override
    public List<MallSubOrderExcelVO> queryErpOrderForExcel(MallSubOrderVO erpOrderQueryVO) {
        return mallSubOrderDOMapper.queryErpOrderForExcel(erpOrderQueryVO);
    }

    //导出子订单2
    @Override
    public List<MallSubOrderExcelVO> queryErpOrderForExcelByIdList(List<Long> idList) {
    	return mallSubOrderDOMapper.queryErpOrderForExcelByIdList(idList);
    }

    @Override
    public List<MallSubOrderDO> list() {
        return mallSubOrderDOMapper.list();

    }

    @Override
    public int selectCountWithStateAndOrderNo(String orderNo, Integer statuts) {
        return mallSubOrderDOMapper.selectCountWithStateAndOrderNo(orderNo, statuts);
    }

    @Override
    public void updateByIsDel(MallSubOrderVO mallSubOrderVO) {
        // TODO Auto-generated method stub

        mallSubOrderDOMapper.updateByIsDel(mallSubOrderVO);
    }

    @Override
    public List<MallSubOrderVO> selectByOrderNoVo(String ordderNo) {
        // TODO Auto-generated method stub
        return mallSubOrderDOMapper.selectByOrderNoVo(ordderNo);
    }

    @Override
    public void deleteByHardSub(Long id) {
        // TODO Auto-generated method stub
        mallSubOrderDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<MallSubOrderDO> queryExpiredSubOrders(Integer status, Long timeOut) {
        return mallSubOrderDOMapper.queryExpiredSubOrders(status,timeOut);
    }

    @Override
    public void updateSubOrderStatus(Integer oldStatus, Integer newStatus, Long timeOut) {
        mallSubOrderDOMapper.updateSubOrderStatus(oldStatus, newStatus,timeOut);
    }

    @Override
    public MallSubOrderDO selectBySubOrderNo(String subOrderNo) {
        return mallSubOrderDOMapper.selectBySubOrderNo(subOrderNo);
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void returns(String subOrderNo) {
        MallSubOrderDO mallSubOrder = mallSubOrderDOMapper.selectBySubOrderNo(subOrderNo);
        MallOrderDO mallOrder = mallOrderMapper.selectByOrderNo(mallSubOrder.getOrderNo());
        if (mallOrder == null){
            throw new ErpCommonException("找不到对应的主订单");
        }
        mallSubOrder.setStatus(OrderStatus.RETURNING.getCode());
        List<MallSubOrderDO> list = mallSubOrderDOMapper.selectByOrderNo(mallOrder.getOrderNo());
        int mallOrderStatus = getMallOrderStatus(list);
        mallOrder.setStatus(mallOrderStatus);
        mallSubOrderDOMapper.updateByPrimaryKeySelective(mallSubOrder);
        mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
        inventoryService.release(mallSubOrder);
        MallReturnOrderDO returnOrder = new MallReturnOrderDO();
        returnOrder.init();
        returnOrder.setOrderNo(mallOrder.getOrderNo());
        returnOrder.setCustomerOpenId(mallOrder.getOpenId());
        returnOrder.setIsCheckin((byte) 1);
        returnOrder.setMallReturnOrderNo(CodeGenUtil.getMallReturnOrderNo());
        returnOrder.setSubOrderNo(mallSubOrder.getSubOrderNo());
        returnOrder.setStatus((byte) OrderReturnStatus.RECEIVE.getCode());
        returnOrder.setReturnQuantity(mallSubOrder.getQuantity());
        returnOrder.setReturnPrice(mallSubOrder.getSalePrice());
        returnOrder.setTelephone(mallSubOrder.getTelephone());
        returnOrder.setReturnReason("其他");
        returnOrder.setReturnReasonDetail("未发货的订单直接取消订单");
        String channelNo = mallOrder.getChannelNo();
        returnOrder.setReturnRefer("102".equals(channelNo)?1:0);
//        （0直接退款;1既退货又退款
        returnOrder.setReturnType(1);
        returnOrderDOMapper.insertSelective(returnOrder);

    }

    @Override
    public MallSubOrderDO getByShippingNo(String shippingNo) {
        return mallSubOrderDOMapper.getByShippingNo(shippingNo);
    }

    @Override
    public void deleteByOrderNo(String orderNo) {
        mallSubOrderDOMapper.deleteByOrderNo(orderNo);
    }

    @Override
    public List<MallSubOrderDO> queryByMallSubOrderNos(List<String> noList) {
        return mallSubOrderDOMapper.queryByMallSubOrderNos(noList);
    }

    @Override
    public Integer changeStatus(Long id, Integer oldStatus,  Integer newStatus) {
        return mallSubOrderDOMapper.changeStatus(id,oldStatus, newStatus);
    }

    private int getMallOrderStatus(List<MallSubOrderDO> list) {
        return OrderStatus.RETURNING.getCode();
    }

    @Override
    public List<MallSubOrderDO> listMallSubOrders(MallSubOrderQueryVO mallSubOrderQueryVO, PageQueryParam pageQueryParam) {
        pageQueryParam.calculateRowIndex();
        return mallSubOrderDOMapper.listMallSubOrders(mallSubOrderQueryVO, pageQueryParam);
    }

    @Override
    public int countMallSubOrders(MallSubOrderQueryVO mallSubOrderQueryVO) {
        return mallSubOrderDOMapper.countMallSubOrders(mallSubOrderQueryVO);
    }
}
