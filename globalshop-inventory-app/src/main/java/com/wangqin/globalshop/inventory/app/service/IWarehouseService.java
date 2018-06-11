package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;

import java.util.List;
import java.util.Map;

public interface IWarehouseService {


	Map<String,Integer> getWarehousePropeties(String companyNo);

    WarehouseDO getWarehouseById(Long warehouseId);

	void addWarehouse(String name);

	void updateWarehouse(WarehouseDO warehouse);

	Object selectById(Long id);

	List<WarehouseDO> queryWarehouses(String companyNo);

	List<WarehouseDO> list(WarehouseDO warehouseDO);
}
