package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.constants.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;

import java.util.List;
import java.util.Map;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface IInventoryOnWarehouseService {
    List<InventoryOnWareHouseDO> queryInventoryAreas(InventoryQueryVO inventoryQueryVO);

    InventoryOnWareHouseDO selectById(Long inventoryAreaId);

    void addInventoryArea(InventoryOnWareHouseDO inventoryArea, InoutOperatorType purchaseIn);

    void inventoryCheckIn(String skuCode, Long warehouseId, String positionNo, Integer quantity);

    List<InventoryOnWareHouseDO> queryInventoryAreaForExcel(InventoryQueryVO inventoryQueryVO);

    void changePositionNo(InventoryOnWareHouseDO inventoryArea);

    InventoryOnWareHouseDO selectByNo(String inventoryAreaId);

    InventoryOnWareHouseDO selectByItemCodeAndSkuCodeAndWarehouseNo(String itemCode, String skuCode, String warehouseNo);

    InventoryOnWareHouseDO insertInventory(InventoryDO inventory, String warehouseNo,String positionNo);

    Map<InventoryOnWareHouseDO,Long> ship(InventoryDO inventoryDO, Long quantity);
}
