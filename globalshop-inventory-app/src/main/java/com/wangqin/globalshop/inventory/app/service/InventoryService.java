package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.*;

/**
 * @author biscuit
 * @data 2018/06/04
 */
public interface InventoryService {
    /**入库*/
    void outbound(InventoryDO inventory,String warehouseNo);
    /**发货*/
    void ship(MallSubOrderDO mallSubOrderDO);
    /**下单*/
    void order(MallOrderDO mallOrderDO);
    /**下单*/
    void order(MallSubOrderDO mallSubOrderDO);
    /**退货*/
    void returns(MallReturnOrderDO mallReturnOrderDO);
    /**出库单出库*/
    void outOfStorehouse(InventoryOutManifestDO outManifestDO);


    InventoryDO selectByItemCodeAndSkuCode(String itemCode, String skuCode);

    void release(MallSubOrderDO order);

    void checkIn(String skuCode, Long warehouseId, String positionNo, Long quantity);

    void inventoryCheckOut(Long inventoryAreaId, Long quantity);
}
