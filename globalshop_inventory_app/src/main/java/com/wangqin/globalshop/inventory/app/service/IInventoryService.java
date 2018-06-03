package com.wangqin.globalshop.inventory.app.service;


import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.vo.InventoryAddVO;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.inventory.app.service.impl.InventoryServiceImpl;

import java.util.List;

public interface IInventoryService {
    InventoryDO queryInventoryBySkuId(String itemId,String skuId) ;

    //void insertBatch(List<InventoryDO> invList);
    InventoryAddVO queryInvBySkuCode(String skuCode);

    void lockVirtualInv(InventoryAddVO inventory);
    public void insertBatchInventory(List<InventoryAddVO> inventoryList);
    //item_module
    void deleteInvBySkuCode(String skuCode);
	void sendInventroyOrder(MallSubOrderDO erpOrder) throws InventoryException;
    InventoryDO queryInventoryBySkuCode(String skuCode);
    InventoryDO queryInventoryBySkuCode(String itemCode, String skuCode);
    void updateVirtualInvByItemCode(String itemCode);
    void update(InventoryDO inventory);

    int releaseInventory(MallSubOrderDO erpOrder) throws InventoryException;



    InventoryServiceImpl.WarehouseCollector selectWarehousesByErpOrder(MallSubOrderDO erpOrder) throws InventoryException;


    /**
     * 未备货的子订单，选定唯一仓库
     * @param erpOrders
     * @return
     * @throws InventoryException
     */
    List<InventoryServiceImpl.WarehouseCollector> selectWarehousesByErpOrders(List<MallSubOrderDO> erpOrders)throws InventoryException ;


    /**
     * 单商品库存,根据提前选择的仓库
     * zhulu
     * @throws InventoryException
     *
     */
    void lockedInventroy(InventoryServiceImpl.WarehouseCollector warehouseCollector) throws InventoryException;


    void transToInventory(Long inventoryAreaId, int toTrans, String positionNo) throws InventoryException;

    Object lockedInventroyErpOrder(MallSubOrderDO order);

    void inventoryCheckOut(String inventoryAreaId, Integer quantity);
}
