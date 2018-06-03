package com.wangqin.globalshop.order.app.service.inventory.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryBookingRecordDOMapperExt;
import com.wangqin.globalshop.common.enums.InventoryRecord;
import com.wangqin.globalshop.order.app.service.inventory.OrderInventoryBookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInventoryBookingRecordServiceImpl implements OrderInventoryBookingRecordService {
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

	@Override
	public void updates(List<InventoryBookingRecordDO> records) {
		for (InventoryBookingRecordDO record : records) {
			mapper.updateByPrimaryKey(record);
		}
	}
}
