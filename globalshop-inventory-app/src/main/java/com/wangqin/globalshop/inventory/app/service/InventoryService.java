package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import net.sf.json.JSONArray;

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
    /**超售入库*/
    void outbound(InventoryDO list);
    /**发货*/
    Map<InventoryOnWareHouseDO, Long> ship(MallSubOrderDO mallSubOrderDO) throws BizCommonException;
    /**下单*/
    void order(MallOrderDO mallOrderDO);
    /**下单*/
    void order(MallSubOrderDO mallSubOrderDO);
    /**第三方下单*/
    void order(List<MallSubOrderDO> outerOrderDetails);
    /**退货*/
    void returns(MallSubOrderDO mallSubOrderDO);

    /**出库单出库*/
    void outOfStorehouse(InventoryOutManifestDO outManifestDO);

    /**SKU修改虚拟库存*/
    void updateVirtualInv(String skuCode,Long virInv,String companyNo);

    InventoryDO selectByItemCodeAndSkuCode(String itemCode, String skuCode);
    /**取消订单库存*/
    void release(MallSubOrderDO order);
    /**尝试取消订单库存，失败不报异常*/
    void tryRelease(MallSubOrderDO mallSubOrderDO);

    /**库存盘入*/
    void inventoryCheckIn(String inventoryOnWarehouseNo, String skuCode, Long quantity);

    /**库存盘入带货架修改*/
    void inventoryCheckIn(String inventoryOnWarehouseNo, String skuCode, Long quantity, String shelfNo);

    /**库存盘出*/
    void inventoryCheckOut(Long inventoryAreaId, Long quantity);

    void inventoryCheckOut(String inventoryOnWarehouseNo, String skuCode, Long quantity);

    /**出库单确认出库*/
    void inventoryOutConfirm(JSONArray inventoryOutDetailArray, String warehouseNo,
                             String warehouseName, String desc);

    /**修改货架号*/
    void updateSelfNo(String inventoryOnWarehouseNo, String selfNo);

    InventoryDO selectBySkuCodeAndCompanyNo(String skuCode, String loginUserCompanyNo);
}
