package com.wangqin.globalshop.order.app.service.warehouse;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;

public interface OrderIWarehouseService  {

	WarehouseDO selectByWarehourseNo(String warehouseNo);
}
