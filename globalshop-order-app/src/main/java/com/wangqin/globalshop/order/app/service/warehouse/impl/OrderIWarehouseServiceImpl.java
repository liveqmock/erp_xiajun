package com.wangqin.globalshop.order.app.service.warehouse.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WareHouseDOMapper;
import com.wangqin.globalshop.order.app.service.warehouse.OrderIWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderIWarehouseServiceImpl implements OrderIWarehouseService {

	@Autowired
	private WareHouseDOMapper mapper;
	@Override
	public WarehouseDO selectByWarehourseNo(String warehouseNo) {
		return mapper.selectByWarehourseNo(warehouseNo);
	}
}
