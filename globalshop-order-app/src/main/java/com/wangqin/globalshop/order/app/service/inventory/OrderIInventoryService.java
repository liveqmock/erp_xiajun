package com.wangqin.globalshop.order.app.service.inventory;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.order.app.service.inventory.impl.OrderIInventoryServiceImpl;

import java.util.List;

public interface OrderIInventoryService{

	void sendInventroyOrder(MallSubOrderDO erpOrder) throws InventoryException;

    InventoryDO queryInventoryBySkuCode(String itemCode, String skuCode);

    void update(InventoryDO inventory);

    int releaseInventory(MallSubOrderDO erpOrder) throws InventoryException;

    List<OrderIInventoryServiceImpl.WarehouseCollector> selectWarehousesByErpOrders(List<MallSubOrderDO> erpOrders) throws InventoryException;

    void lockedInventroy(OrderIInventoryServiceImpl.WarehouseCollector wc) throws InventoryException;

    OrderIInventoryServiceImpl.WarehouseCollector selectWarehousesByErpOrder(MallSubOrderDO erpOrder) throws InventoryException;
}
