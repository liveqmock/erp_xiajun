package com.wangqin.globalshop.channel.service.inventory;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.wangqin.globalshop.biz1.app.constants.enums.StockUpStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryBookingRecordDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOnWarehouseMapperExt;
import com.wangqin.globalshop.channel.Exception.InventoryException;
import com.wangqin.globalshop.channel.dal.dataObjectVo.InventoryVo;
import com.wangqin.globalshop.channel.service.order.ChannelIMallSubOrderService;
import com.wangqin.globalshop.channel.service.warehouse.IWarehouseService;
import com.wangqin.globalshop.common.enums.InventoryRecord;
import com.wangqin.globalshop.common.utils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * Inventory 表数据服务层接口实现类
 *
 */
@Service("inventoryService")
public class InventoryServiceImpl  implements IInventoryService {

	@Autowired
	private InventoryMapperExt inventoryDOMapperExt;

	@Autowired
	private InventoryOnWarehouseMapperExt inventoryOnWarehouseMapperExt;

	@Autowired
	private InventoryBookingRecordDOMapperExt inventoryBookingRecordDOMapperExt;

	@Autowired IWarehouseService warehouseService;

	@Autowired
    ChannelIMallSubOrderService mallSubOrderService;


	public InventoryDOMapper getMapper(){
		return inventoryDOMapperExt;
	}


	public int deleteByPrimaryKey(Long id){
		return inventoryDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(InventoryDO record){
		return inventoryDOMapperExt.insert(record);
	}

	public int insertSelective(InventoryDO record){
		return inventoryDOMapperExt.insertSelective(record);
	}

	public InventoryDO selectByPrimaryKey(Long id){
		return inventoryDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(InventoryDO record){
		return inventoryDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(InventoryDO record){
		return inventoryDOMapperExt.updateByPrimaryKey(record);
	}



	@Override
	public InventoryDO queryInventoryByCode(String itemCode, String skuCode) {
		return inventoryDOMapperExt.queryInventoryByCode(itemCode, skuCode);
	}



	public static class WarehouseCollector {

		public static final int STOCKSTATUS_NOT_READY = StockUpStatus.INIT.getCode();// 未备货
		public static final int STOCKSTATUS_PART_READY = StockUpStatus.PART.getCode();// 部分备货
		public static final int STOCKSTATUS_FINISHED = StockUpStatus.STOCKUP.getCode();// 备货完成
		public static final int STOCKSTATUS_TRANS_STOCKUP = StockUpStatus.TRANS_STOCKUP.getCode();// 全部在途备货
		public static final int STOCKSTATUS_TRANS_PART = StockUpStatus.TRANS_PART.getCode();// 部分在途备货
		public static final int STOCKSTATUS_MIX_STOCKUP = StockUpStatus.MIX_STOCKUP.getCode();// 混合备货完成

		private String orderNo;
		private Long erpOrderId;
		private Integer stockStatus;// 备货状态 0:未备货 1:部分备货 2 备货完成
		private Long booked;// 本次预定量（实际）
		private Long transBooked;// 本次在途预定量（在途）
		private Long lastBooked; //上次预定在途（数据库）
		private Long lastTransBooked; //上次预定在途（数据库）
		private String warehouseNo;//仓库ID
		private Long quantity;//erporder 购买数量
		private MallSubOrderDO erpOrder;
		//		private Map<Long, Integer> warehouseMap;// 实际
		//		private Map<Long, Integer> transWarehouseMap;// 在途
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public Long getErpOrderId() {
			return erpOrderId;
		}
		public void setErpOrderId(Long erpOrderId) {
			this.erpOrderId = erpOrderId;
		}
		public MallSubOrderDO getErpOrder() {
			return erpOrder;
		}
		public void setErpOrder(MallSubOrderDO erpOrder) {
			this.erpOrder = erpOrder;
		}
		public Integer getStockStatus() {
			return stockStatus;
		}

		public void setStockStatus(Integer stockStatus) {
			this.stockStatus = stockStatus;
		}
		public Long getBooked() {
			return booked;
		}
		public void setBooked(Long booked) {
			this.booked = booked;
		}
		public Long getTransBooked() {
			return transBooked;
		}
		public void setTransBooked(Long transBooked) {
			this.transBooked = transBooked;
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
		public String getWarehouseNo() {
			return warehouseNo;
		}
		public void setWarehouseNo(String warehouseNo) {
			this.warehouseNo = warehouseNo;
		}
		public Long getQuantity() {
			return quantity;
		}
		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}
	}



	@Override
	public WarehouseCollector selectWarehousesByErpOrder(MallSubOrderDO erpOrder) throws InventoryException {
		if(erpOrder.getWarehouseNo()==null){
			throw new InventoryException(
					String.format("子订单没有备货的仓库：erporderid=[%d],erpno=[%s]", erpOrder.getId(), erpOrder.getOrderNo()+erpOrder.getSubOrderNo()));
		}
		WarehouseCollector warehouseColl = new WarehouseCollector();
		warehouseColl.setErpOrderId(erpOrder.getId());
		warehouseColl.setOrderNo(erpOrder.getOrderNo());
		warehouseColl.setErpOrder(erpOrder);
		warehouseColl.setQuantity(Long.valueOf(erpOrder.getQuantity()));
		warehouseColl.setWarehouseNo(erpOrder.getWarehouseNo());

		// 从预定记录里面查询已经预定的量
		List<InventoryBookingRecordDO> inventoryRecords = inventoryBookingRecordDOMapperExt
				.sumBookedByInventoryType(erpOrder.getOrderNo());
		if(CollectionUtils.isEmpty(inventoryRecords)){
			throw new InventoryException(
					String.format("子订单没有备货的记录：erporderid=[%d],erpno=[%s]", erpOrder.getId(), erpOrder.getOrderNo()+erpOrder.getSubOrderNo()));
		}
		Long lastBooked = 0L;
		Long lastTransBooked = 0L;
		for(InventoryBookingRecordDO inventoryRecord:inventoryRecords){
			if(inventoryRecord.getInventoryType()==InventoryRecord.InventoryType.TRANS_INV){
				lastTransBooked = inventoryRecord.getBookedQuantity();
			} else if(inventoryRecord.getInventoryType()==InventoryRecord.InventoryType.INVENTORY){
				lastBooked = inventoryRecord.getBookedQuantity();
			}
		}
		warehouseColl.setLastBooked(lastBooked);
		warehouseColl.setLastTransBooked(lastTransBooked);

		List<InventoryOnWareHouseDO> sumInventorys = inventoryOnWarehouseMapperExt.sumInventoryBySkuIdGroupbyWarehouse(erpOrder.getItemCode(),
				erpOrder.getSkuCode(),warehouseColl.getWarehouseNo());

		if(CollectionUtils.isNotEmpty(sumInventorys)){
			InventoryOnWareHouseDO inventoryArea  = sumInventorys.get(0);
			if(inventoryArea.getTotalAvailableInv()>0){
				initBooked(inventoryArea, warehouseColl);
			}
		}
		return warehouseColl;
	}





	public List<WarehouseCollector> selectWarehousesByErpOrders(List<MallSubOrderDO> erpOrders) throws InventoryException  {
		if(CollectionUtils.isEmpty(erpOrders)){
			return null;
		}
		//Long:仓库ID,Long:erporderid,WarehouseCollector
		Table<String, Long, WarehouseCollector> table = HashBasedTable.create();

		String companyNo = erpOrders.get(0).getCompanyNo();

		Map<String,Integer> warehouseSeqMap = warehouseService.getWarehousePropeties(companyNo);

		for (MallSubOrderDO erpOrder : erpOrders) {
			if (erpOrder.getWarehouseNo() != null) {
				throw new InventoryException(
						String.format("包含已经备货的仓库：erporderid=[%d],erpno=[%s]", erpOrder.getId(), erpOrder.getOrderNo()));
			}
			List<InventoryOnWareHouseDO> sumInventorys = inventoryOnWarehouseMapperExt.sumInventoryBySkuIdGroupbyWarehouse(erpOrder.getItemCode(),
					erpOrder.getSkuCode(),null);
			if (CollectionUtils.isNotEmpty(sumInventorys)) {
				for (InventoryOnWareHouseDO inventory : sumInventorys) {

					Long totalAvaillableInv = inventory.getInventory()-inventory.getLockedInv()+inventory.getTransInv()-inventory.getLockedTransInv();

					if (totalAvaillableInv > 0) {
						WarehouseCollector whc = new WarehouseCollector();
						whc.setErpOrderId(erpOrder.getId());
						whc.setOrderNo(erpOrder.getOrderNo());
						whc.setWarehouseNo(String.valueOf(inventory.getWarehouseNo()));
						whc.setQuantity(Long.valueOf(erpOrder.getQuantity()));
						whc.setErpOrder(erpOrder);
						initBooked(inventory, whc);
						table.put(String.valueOf(inventory.getWarehouseNo()), whc.getErpOrderId(), whc);
					}
				}
			}
		}
		// 计算每个仓库下面的得分。
		if (!table.isEmpty()) {
			Map<String, Double> scoreMap = Maps.newHashMap();
			Set<String> rows = table.rowKeySet();
			for (String wareNo : rows) {
				Map<Long, WarehouseCollector> rowData = table.row(wareNo);
				for (Map.Entry<Long, WarehouseCollector> entry : rowData.entrySet()) {
					WarehouseCollector whc = entry.getValue();
					if (scoreMap.containsKey(wareNo)) {
						Double d = scoreMap.get(wareNo) + score(whc);
						scoreMap.put(wareNo, d);
					} else {
						scoreMap.put(wareNo, score(whc));
					}
				}
			}
			String maxWareNo = null;
			Double maxD = null;
			for (String wareNo : scoreMap.keySet()) {
				if (maxD == null) {
					maxWareNo = wareNo;
					maxD = scoreMap.get(wareNo);
				} else {
					if (maxD < scoreMap.get(wareNo)) {
						maxD = scoreMap.get(wareNo);
						maxWareNo = wareNo;
					} else if(maxD.equals(scoreMap.get(wareNo)) && warehouseSeqMap.get(wareNo) > warehouseSeqMap.get(maxWareNo)) {
						maxD = scoreMap.get(wareNo);
						maxWareNo = wareNo;
					}
				}
			}
			if (maxWareNo != null) {
				List<WarehouseCollector> wcs = Lists.newArrayList();
				Map<Long, WarehouseCollector> rowData = table.row(maxWareNo);
				for (Map.Entry<Long, WarehouseCollector> entry : rowData.entrySet()) {
					wcs.add(entry.getValue());
				}
				return wcs;
			}else{
				return null;
			}
		}
		return null;
	}


	/**
	 * 计算在指定仓库可以备货的库存数量
	 * @param inventory
	 * @param whc
	 */
	private void initBooked(InventoryOnWareHouseDO inventory, WarehouseCollector whc) {

		Long needBook = whc.getQuantity()-whc.getLastBooked()-whc.getLastTransBooked();
		Long availableInv = inventory.getInventory()-inventory.getLockedInv();
		Long availableTransInv = inventory.getTransInv()-inventory.getLockedTransInv();

		if (availableInv > 0) {
			if ((needBook - availableInv) > 0) {
				needBook = needBook - availableInv;
				whc.setBooked(availableInv);
			} else {
				whc.setBooked(needBook);
				needBook=0L;
			}
		}
		if (needBook > 0) {
			if (availableTransInv > 0) {
				if ((needBook - availableTransInv) > 0) {
					whc.setTransBooked(availableTransInv);
				} else {
					whc.setTransBooked(needBook);
				}

			}

		}
	}


	private Double score(WarehouseCollector whc) {
		Long qua = whc.getQuantity();
		//int allBooked = whc.getTransBooked()+whc.getBooked();		//在途不参与计分
		Long allBooked = whc.getBooked();
		if(allBooked==0){
			return 0d;
		}
		if(qua==allBooked){//全部备货完成返回10分
			return 10d;
		}else{//部分备货按照比例返回 (备货数量/总数) * 10
			return allBooked/(qua*0.1d);
		}
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
		InventoryDO inventory = inventoryDOMapperExt.queryInventoryByCode(itemCode, skuCode);
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
		int records = inventoryDOMapperExt.updateLockInventory(inventoryVo.getId(), warehouseCollector.getBooked(), warehouseCollector.getTransBooked());
		if (records == 0) {
			throw new InventoryException(String.format(
					"data expiration exception : detailId[%d],StockStatus[%d],CalcInventory[%d],ActualInventory[%d],CalcTransInventory[%d],ActualTransInventory[%d]",
					warehouseCollector.getErpOrder().getId(), warehouseCollector.getStockStatus(), warehouseCollector.getBooked(), availableInv,
					inventoryVo.getAvailableTransInv(), availableTransInv));
		}

		ErpOrderUtil.calculateStockStatus(erpOrder, warehouseCollector.getBooked()+warehouseCollector.getLastBooked(), warehouseCollector.getLastTransBooked()+warehouseCollector.getTransBooked());
		erpOrder.setGmtModify(new Date());
		erpOrder.setWarehouseNo(warehouseCollector.getWarehouseNo());
		mallSubOrderService.updateByPrimaryKey(erpOrder);//更新状态
	}
	/**
	 * 实际库存锁定（备货，预分配）
	 * @param erpOrder
	 * @param warehouseCollector
	 * @param availableInv
	 * @throws InventoryException
	 */
	private void insertInventoryLockedRecord(MallSubOrderDO erpOrder, WarehouseCollector warehouseCollector, Long availableInv)
			throws InventoryException {
		String itemCode = erpOrder.getItemCode();
		String skuCode = erpOrder.getSkuCode();
		String warehouseNo = warehouseCollector.getWarehouseNo();
		Long booked = warehouseCollector.getBooked();
		List<InventoryOnWareHouseDO> areas = inventoryOnWarehouseMapperExt.queryInventoryAreaByWarehouse(warehouseNo, itemCode, skuCode);
		for (InventoryOnWareHouseDO inventoryArea : areas) {
			Long available = inventoryArea.getInventory()-inventoryArea.getLockedInv();
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
			inventoryOnWarehouseMapperExt.updateByPrimaryKey(inventoryArea);
			inventoryBookingRecordDOMapperExt
					.insert(buildLockedRecord(erpOrder, bookedArea, inventoryArea, skuCode, InventoryRecord.InventoryType.INVENTORY));
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
	 * 在途库存锁定（预分配，备货）
	 * @param erpOrder
	 * @param warehouseCollector
	 * @param availableInv
	 * @throws InventoryException
	 */
	private void insertTransInventoryLockedRecord(MallSubOrderDO erpOrder, WarehouseCollector warehouseCollector,
			Long availableInv) throws InventoryException {
		String itemCode = erpOrder.getItemCode();
		String skuCode = erpOrder.getSkuCode();
		String warehouseNo = warehouseCollector.getWarehouseNo();
		Long booked = warehouseCollector.getTransBooked();
		List<InventoryOnWareHouseDO> areas = inventoryOnWarehouseMapperExt.queryInventoryAreaByWarehouse(warehouseNo, itemCode, skuCode);
		for (InventoryOnWareHouseDO inventoryArea : areas) {
			Long available = inventoryArea.getTransInv()-inventoryArea.getLockedTransInv();
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
			inventoryOnWarehouseMapperExt.updateByPrimaryKey(inventoryArea);
			inventoryBookingRecordDOMapperExt
					.insert(buildLockedRecord(erpOrder, bookedArea, inventoryArea, skuCode, InventoryRecord.InventoryType.TRANS_INV));
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

	private InventoryBookingRecordDO buildLockedRecord(MallSubOrderDO erpOrder, Long booked, InventoryOnWareHouseDO inventory, String skuCode,
			String inventoryType) {
		InventoryBookingRecordDO bookedRecord = new InventoryBookingRecordDO();
		bookedRecord.setOrderNo(erpOrder.getOrderNo());
		bookedRecord.setSubOrderNo(erpOrder.getSubOrderNo());
		bookedRecord.setSkuCode(skuCode);
		bookedRecord.setItemCode(erpOrder.getItemCode());
		bookedRecord.setSkuCode(erpOrder.getSkuCode());
		bookedRecord.setInventory(inventory.getInventory());
		bookedRecord.setPositionNo(inventory.getShelfNo());
		bookedRecord.setBookedQuantity(booked);
		bookedRecord.setInventoryAreaId(inventory.getId());
		bookedRecord.setInventoryOnWarehouseNo(inventory.getWarehouseNo());
		bookedRecord.setQuantity(Long.valueOf(erpOrder.getQuantity()));
		bookedRecord.setInventoryType(inventoryType);
		bookedRecord.setOperatorType(InventoryRecord.OperatorType.LOCKED);
		Date now = new Date();
		bookedRecord.setGmtCreate(now);
		bookedRecord.setGmtModify(now);
		return bookedRecord;
	}

}
