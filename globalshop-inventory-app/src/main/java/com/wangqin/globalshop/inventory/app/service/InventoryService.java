package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;

import java.util.List;

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
    void ship(MallSubOrderDO mallSubOrderDO) throws ErpCommonException;
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

    void checkIn(String skuCode, Long warehouseId, String positionNo, Long quantity);

    void inventoryCheckOut(Long inventoryAreaId, Long quantity);


    InventoryDO selectBySkuCodeAndCompanyNo(String skuCode, String loginUserCompanyNo);
}
