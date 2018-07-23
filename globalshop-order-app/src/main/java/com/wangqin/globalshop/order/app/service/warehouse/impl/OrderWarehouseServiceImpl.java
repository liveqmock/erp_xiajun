package com.wangqin.globalshop.order.app.service.warehouse.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.WarehouseDOMapperExt;
import com.wangqin.globalshop.order.app.service.warehouse.IOrderWarehouseService;

@Service
public class OrderWarehouseServiceImpl implements IOrderWarehouseService {

	@Autowired
	private WarehouseDOMapperExt mapper;
	@Override
	public WarehouseDO selectByWarehouseNo(String warehouseNo) {
		return mapper.selectByWarehouseNo(warehouseNo);
	}
	
	@Override
	public List<WarehouseDO> selectByCompanyNo(String warehouseNo) {
		return mapper.selectByCompanyNo(warehouseNo);
	}
}
