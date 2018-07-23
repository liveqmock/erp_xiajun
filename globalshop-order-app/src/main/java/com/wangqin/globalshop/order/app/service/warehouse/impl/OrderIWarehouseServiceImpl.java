package com.wangqin.globalshop.order.app.service.warehouse.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.WarehouseDOMapperExt;
import com.wangqin.globalshop.order.app.service.warehouse.OrderIWarehouseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderIWarehouseServiceImpl implements OrderIWarehouseService {

	@Autowired
	private WarehouseDOMapperExt mapper;
	@Override
	public WarehouseDO selectByWarehouseNo(String warehouseNo) {
		return mapper.selectByWarehouseNo(warehouseNo);
	}
	
	@Override
	public List<WarehouseDO> selectByCompanyNo(String companyNo) {
		return mapper.selectByCompanyNo(companyNo);
	}
}
