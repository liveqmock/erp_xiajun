package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;

import java.util.Map;

public interface IWarehouseService {

	WarehouseDO selectByWarehourseNo(String warehouseNo);

	Map<String,Integer> getWarehousePropeties(String companyNo);

    WarehouseDO getWarehouseById(Long warehouseId);
}
