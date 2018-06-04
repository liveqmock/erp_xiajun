package com.wangqin.globalshop.inventory.app.service.impl;

import com.wangqin.globalshop.biz1.app.constants.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOnWarehouseMapperExt;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.inventory.app.service.IInventoryOnWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/01
 */
@Service
public class InventoryOnWarehouseServiceImpl implements IInventoryOnWarehouseService {

    @Autowired
    private InventoryOnWarehouseMapperExt mapper;
    @Override
    public JsonPageResult<List<InventoryOnWareHouseDO>> queryInventoryAreas(InventoryQueryVO inventoryQueryVO) {
        return null;
    }

    @Override
    public InventoryOnWareHouseDO selectById(Long inventoryAreaId) {
        return null;
    }

    @Override
    public void addInventoryArea(InventoryOnWareHouseDO inventoryArea, InoutOperatorType purchaseIn) {

    }

    @Override
    public void inventoryCheckIn(String skuCode, Long warehouseId, String positionNo, Integer quantity) {

    }

    @Override
    public List<InventoryOnWareHouseDO> queryInventoryAreaForExcel(InventoryQueryVO inventoryQueryVO) {
        return null;
    }

    @Override
    public void changePositionNo(InventoryOnWareHouseDO inventoryArea) {

    }

    @Override
    public InventoryOnWareHouseDO selectByNo(String inventoryAreaId) {
        return null;
    }
    //---------------------------------------------------------------new ----------------------------------
    @Override
    public InventoryOnWareHouseDO selectByItemCodeAndSkuCodeAndWarehouseNo(String itemCode, String skuCode, String warehouseNo) {
        return mapper.selectByItemCodeAndSkuCodeAndWarehouseNo(itemCode,skuCode,warehouseNo);
    }

    @Override
    public void insertInventory(InventoryDO inventory, String warehouseNo) {

        InventoryOnWareHouseDO warehouse = mapper.selectByItemCodeAndSkuCodeAndWarehouseNo(inventory.getSkuCode(), inventory.getItemCode(),warehouseNo);
        if (warehouse == null) {
            warehouse = new InventoryOnWareHouseDO();
            warehouse.setSkuCode(inventory.getSkuCode());
            warehouse.setItemCode(inventory.getItemCode());
            warehouse.setWarehouseNo(warehouseNo);
            warehouse.setGmtCreate(new Date());
            mapper.insertSelective(warehouse);
        } else {
            warehouse.setInventory(warehouse.getInventory() + inventory.getInv());
            mapper.updateByPrimaryKeySelective(warehouse);
        }
    }
}
