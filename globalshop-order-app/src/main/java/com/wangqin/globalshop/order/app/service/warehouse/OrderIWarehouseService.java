package com.wangqin.globalshop.order.app.service.warehouse;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;

public interface OrderIWarehouseService  {

	WarehouseDO selectByWarehouseNo(String warehouseNo);
	
	List<WarehouseDO> selectByCompanyNo(String companyNo);
	
}
