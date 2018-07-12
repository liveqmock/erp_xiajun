package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;

import java.util.List;
import java.util.Map;

/**
 * @author biscuit
 * @data 2018/06/04
 */
public interface InventoryService {
    /**入库*/
    void outbound(InventoryDO inventory,String warehouseNo,String positionNo);
    /**超售入库*/
    void outbound(List<InventoryDO> list);
    /**发货*/
    Map<InventoryOnWareHouseDO, Long> ship(MallSubOrderDO mallSubOrderDO) throws ErpCommonException;
    /**下单*/
    void order(MallOrderDO mallOrderDO);
    /**下单*/
    void order(MallSubOrderDO mallSubOrderDO);
    /**第三方下单*/
    void order(List<MallSubOrderDO> outerOrderDetails);
    /**退货*/
    void returns(MallSubOrderDO mallReturnOrderDO,Long inv);
    /**出库单出库*/
    void outOfStorehouse(InventoryOutManifestDO outManifestDO);
    /**SKU修改虚拟库存*/
    void updateVirtualInv(String skuCode,Long virInv,String companyNo);

    InventoryDO selectByItemCodeAndSkuCode(String itemCode, String skuCode);
    /**取消订单*/
    void release(MallSubOrderDO order);

    /**库存盘入*/
    void inventoryCheckIn(String inventoryOnWarehouseNo, String skuCode, Long quantity);

    /**库存盘入带货架修改*/
    void inventoryCheckIn(String inventoryOnWarehouseNo, String skuCode, Long quantity, String shelfNo);

    /**库存盘出*/
    void inventoryCheckOut(Long inventoryAreaId, Long quantity);

    void inventoryCheckOut(String inventoryOnWarehouseNo, String skuCode, Long quantity);

    /**修改货架号*/
    void updateSelfNo(String inventoryOnWarehouseNo, String selfNo);

    InventoryDO selectBySkuCodeAndCompanyNo(String skuCode, String loginUserCompanyNo);
}
