package com.wangqin.globalshop.order.app.service.inventory;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.common.exception.InventoryException;

public interface OrderIInventoryService{

	void sendInventroyOrder(MallSubOrderDO erpOrder) throws InventoryException;
}
