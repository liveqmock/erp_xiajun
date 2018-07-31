package com.wangqin.globalshop.inventory.app.service;

import java.util.List;
import java.util.Map;

import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface IInventoryOnWarehouseService {

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

    /**
     * 根据指定条件分页查询库存记录列表
     * @param inventoryOnWarehouseQueryVO
     * @param pageQueryParam
     * @return
     */
    List<InventoryOnWarehouseItemVO> listInventoryOnWarehouse(InventoryOnWarehouseQueryVO inventoryOnWarehouseQueryVO,
                                                              PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询库存记录数目
     * @param inventoryOnWarehouseQueryVO
     * @return
     */
    int countInventoryOnWarehouse(InventoryOnWarehouseQueryVO inventoryOnWarehouseQueryVO);
}
