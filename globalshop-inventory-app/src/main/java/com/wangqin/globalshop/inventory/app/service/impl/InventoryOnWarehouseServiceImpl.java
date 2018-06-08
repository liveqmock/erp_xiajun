package com.wangqin.globalshop.inventory.app.service.impl;

import com.wangqin.globalshop.biz1.app.constants.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOnWarehouseMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.WarehouseDOMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.inventory.app.service.IInventoryOnWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author biscuit
 * @data 2018/06/01
 */
@Service
public class InventoryOnWarehouseServiceImpl implements IInventoryOnWarehouseService {

    @Autowired
    private InventoryOnWarehouseMapperExt mapper;
    @Autowired
    private WarehouseDOMapperExt warehouseMapper;
    @Autowired
    private ItemSkuMapperExt itemSkuMapper;

    @Override
    public List<InventoryOnWareHouseDO> queryInventoryAreas(InventoryQueryVO inventoryQueryVO) {
        return mapper.queryInventoryAreas(inventoryQueryVO);
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
        return mapper.selectByItemCodeAndSkuCodeAndWarehouseNo(itemCode, skuCode, warehouseNo);
    }

    @Override
    public InventoryOnWareHouseDO insertInventory(InventoryDO inventory,Long inv, String warehouseNo, String positionNo) {

        InventoryOnWareHouseDO warehouse = mapper.selectByItemCodeAndSkuCodeAndWarehouseNo(inventory.getSkuCode(), inventory.getItemCode(), warehouseNo);
        if (warehouse == null) {
            WarehouseDO warehouseDO = warehouseMapper.selectByWarehouseNo(warehouseNo);
            ItemSkuDO itemSkuDO = itemSkuMapper.queryItemBySkuCode(inventory.getSkuCode());
            warehouse = new InventoryOnWareHouseDO();
            warehouse.setUpc(itemSkuDO.getUpc());
            warehouse.setItemName(itemSkuDO.getItemName());
            warehouse.setScale(itemSkuDO.getScale());
            warehouse.setSkuPic(itemSkuDO.getSkuPic());
            warehouse.setShelfNo(positionNo);
            warehouse.setCompanyNo("InvOnWarehouseServiceImpl1321");
            warehouse.setInventoryOnWarehouseNo("INVONWARE" + System.currentTimeMillis());
            warehouse.setInventory(inv);
            warehouse.setSkuCode(inventory.getSkuCode());
            warehouse.setItemCode(inventory.getItemCode());
            warehouse.setWarehouseName(warehouseDO.getName());
            warehouse.setWarehouseNo(warehouseNo);
            warehouse.init();
            warehouse.setBatchNo("123213");
            mapper.insertSelective(warehouse);
        } else {
            warehouse.setInventory(warehouse.getInventory() + inv);
            mapper.updateByPrimaryKeySelective(warehouse);
        }
        return warehouse;
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public Map<InventoryOnWareHouseDO, Long> ship(InventoryDO inventoryDO, Long quantity) {
        //按照升序获得所有和该商品相关  该公司的记录
        List<InventoryOnWareHouseDO> list = mapper.selectBySkuCode(inventoryDO.getSkuCode());
        if (list.size()==0){
            throw new ErpCommonException("找不到相关商品库存");
        }
//        List<InventoryOnWareHouseDO> list = mapper.getINvOnWarehouseListOfShip(inventoryDO.getSkuCode(), inventoryDO.getCompanyNo());
        Map<InventoryOnWareHouseDO, Long> map = chooseWarehouse(list, quantity);
        for (InventoryOnWareHouseDO inventoryOnWareHouseDO : map.keySet()) {
            inventoryOnWareHouseDO.setInventory(inventoryOnWareHouseDO.getInventory()-map.get(inventoryOnWareHouseDO));
            mapper.updateByPrimaryKeySelective(inventoryOnWareHouseDO);
        }
        return map;
    }
    /**Map   Long的意思是需要出的*/
    private Map<InventoryOnWareHouseDO, Long> chooseWarehouse(List<InventoryOnWareHouseDO> list, Long quantity) {
        //        有限找刚好够分配的记录
        //如果不存在   则向前找  尽量从少的仓库发货
        Map<InventoryOnWareHouseDO, Long> result = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getInventory() >= quantity) {
                result.put(list.get(i),quantity);
                return result;
            }
        }
        if (list.size() == 1){
            throw new ErpCommonException("库存不足 发货失败");
        }
//        Long num = 0L;
//        for (int i = list.size(); i > 0; i--) {
//            num += list.get(i - 1).getInventory();
//            if(num < quantity){
//                result.put(list.get(i), list.get(i).getInventory());
//            }
//            else  {
//                result.put(list.get(i), list.get(i).getInventory()+ quantity - num );
//                return result;
//            }
//
//        }
        throw new ErpCommonException("发货失败");

    }
}
