package com.wangqin.globalshop.order.app.service.inventory.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryBookingRecordDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryBookingRecordDOMapperExt;
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
}
