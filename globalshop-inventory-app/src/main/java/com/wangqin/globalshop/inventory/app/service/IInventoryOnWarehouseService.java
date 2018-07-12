package com.wangqin.globalshop.inventory.app.service;

import java.util.List;
import java.util.Map;

import com.wangqin.globalshop.biz1.app.constants.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryOnWarehouseVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface IInventoryOnWarehouseService {
    List<InventoryOnWarehouseVO> queryInventoryAreas(InventoryQueryVO inventoryQueryVO);

    InventoryOnWareHouseDO selectById(Long inventoryAreaId);

    void addInventoryArea(InventoryOnWareHouseDO inventoryArea, InoutOperatorType purchaseIn);

    void inventoryCheckIn(String skuCode, Long warehouseId, String positionNo, Integer quantity);

    List<InventoryOnWareHouseDO> queryInventoryAreaForExcel(InventoryQueryVO inventoryQueryVO);

    void changePositionNo(InventoryOnWareHouseDO inventoryArea);

    InventoryOnWareHouseDO selectByNo(String inventoryAreaId);

    InventoryOnWareHouseDO selectByCompanyNoAndSkuCodeAndWarehouseNo(String companyNo, String skuCode, String warehouseNo);

    InventoryOnWareHouseDO insertInventory(InventoryDO inventory,Long inv, String warehouseNo,String positionNo);

    Map<InventoryOnWareHouseDO,Long> ship(InventoryDO inventoryDO, Long quantity);

    List<InventoryOnWareHouseDO> selectByCompanyNoAndSkuCode(String companyNo, String skuCode);

    void order(InventoryDO inventoryDO, Integer quantity);

    InventoryOnWareHouseDO selectByWarehouseNo(String warehouseNo);
    
    InventoryOnWareHouseDO selectByInventoryOnWarehouseNo(String inventoryOnWarehouseNo);
}
