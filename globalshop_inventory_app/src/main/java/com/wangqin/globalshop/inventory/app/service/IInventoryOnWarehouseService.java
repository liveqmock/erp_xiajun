package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.constants.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface IInventoryOnWarehouseService {
    JsonPageResult<List<InventoryOnWareHouseDO>> queryInventoryAreas(InventoryQueryVO inventoryQueryVO);

    InventoryOnWareHouseDO selectById(Long inventoryAreaId);

    void addInventoryArea(InventoryOnWareHouseDO inventoryArea, InoutOperatorType purchaseIn);

    void inventoryCheckIn(String skuCode, Long warehouseId, String positionNo, Integer quantity);

    List<InventoryOnWareHouseDO> queryInventoryAreaForExcel(InventoryQueryVO inventoryQueryVO);

    void changePositionNo(InventoryOnWareHouseDO inventoryArea);
}
