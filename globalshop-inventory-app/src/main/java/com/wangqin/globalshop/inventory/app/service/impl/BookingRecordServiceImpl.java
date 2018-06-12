package com.wangqin.globalshop.inventory.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryBookingRecordDOMapperExt;
import com.wangqin.globalshop.inventory.app.service.InventoryBookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingRecordServiceImpl implements InventoryBookingRecordService {
	@Autowired
	private InventoryBookingRecordDOMapperExt mapper;
	@Override
	public List<InventoryBookingRecordDO> queryByErpOrderId(String orderNo) {
		return mapper.selectByOrderNo(orderNo);
	}

	@Override
	public void delete(InventoryBookingRecordDO inventoryRecord) {
		mapper.deleteByPrimaryKey(inventoryRecord.getId());
	}

	@Override
	public void insert(InventoryBookingRecordDO inventoryRecord) {
		mapper.insert(inventoryRecord);
	}

	@Override
	public List<InventoryBookingRecordDO> sumBookedByInventoryType(String orderNo) {
		return mapper.sumBookedByInventoryType(orderNo);
	}

//	/**
//	 * 计算分仓库总库存量
//	 *
//	 * @param itemId
//	 *            商品ID ，后续分库分表可使用
//	 * @param skuId
//	 *            唯一商品型号 类似skucode
//	 * @return
//	 */
//	//@Override
//	//public List<InventoryOnWareHouseDO> sumInventoryBySkuIdGroupbyWarehouse(String itemCode, String skuCode, Object o) {
//	/	return null;
//	}

	@Override
	public InventoryBookingRecordDO queryById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<InventoryBookingRecordDO> queryByOnWarehouseNoAndInventoryType(String inventoryOnWarehouseNo, String transInv) {
		return mapper.queryByOnWarehouseNoAndInventoryType(inventoryOnWarehouseNo,transInv);
	}

	@Override
	public void updateSelectiveById(InventoryBookingRecordDO inventoryRecord) {
		mapper.updateByPrimaryKeySelective(inventoryRecord);
	}

	@Override
	public List<InventoryBookingRecordDO> sumInventoryCheckRecord(Long id, String transInv, List<Integer> integers) {
		return null;
	}

	@Override
	public List<InventoryOnWareHouseDO> selectByCompanyNoAndSkuCode(String companyNo, String skuCode) {
		return mapper.selectByCompanyNoAndSkuCode(companyNo,skuCode);
	}

	@Override
	public List<InventoryOnWareHouseDO> selectByCompanyNo(String companyNo) {
		return mapper.selectByCompanyNo(companyNo);
	}

	@Override
	public List<InventoryOnWareHouseDO> sumInventoryBySkuIdGroupbyWarehouse(String itemCode, String skuCode, Object o) {
		// TODO Auto-generated method stub
		return null;
	}


}
