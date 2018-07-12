package com.wangqin.globalshop.inventory.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangqin.globalshop.common.utils.CodeGenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangqin.globalshop.biz1.app.constants.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryOnWarehouseVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOnWarehouseMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.WarehouseDOMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.inventory.app.service.IInventoryOnWarehouseService;



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
    public List<InventoryOnWarehouseVO> queryInventoryAreas(InventoryQueryVO inventoryQueryVO) {
        inventoryQueryVO.init();
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
    public InventoryOnWareHouseDO selectByCompanyNoAndSkuCodeAndWarehouseNo(String companyNo, String skuCode, String warehouseNo) {
        return mapper.selectByCompanyNoAndSkuCodeAndWarehouseNo(companyNo, skuCode, warehouseNo);
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public InventoryOnWareHouseDO insertInventory(InventoryDO inventory,Long inv, String warehouseNo, String positionNo) {
        inventory.initCompany();
        InventoryOnWareHouseDO warehouse = mapper.selectByCompanyNoAndSkuCodeAndWarehouseNo(inventory.getCompanyNo(),inventory.getSkuCode(), warehouseNo);
        if (warehouse == null) {
            WarehouseDO warehouseDO = warehouseMapper.selectByWarehouseNo(warehouseNo);
            ItemSkuDO itemSkuDO = itemSkuMapper.queryItemBySkuCode(inventory.getSkuCode());
            warehouse = new InventoryOnWareHouseDO();
            warehouse.setUpc(itemSkuDO.getUpc());
            warehouse.setItemName(itemSkuDO.getItemName());
            warehouse.setScale(itemSkuDO.getScale());
            warehouse.setSkuPic(itemSkuDO.getSkuPic());
            warehouse.setShelfNo(positionNo);
            warehouse.setInventoryOnWarehouseNo(CodeGenUtil.getInvOnWarehouseNo());
            warehouse.setInventory(inv);
            warehouse.setSkuCode(inventory.getSkuCode());
            warehouse.setItemCode(inventory.getItemCode());
            warehouse.setWarehouseName(warehouseDO.getName());
            warehouse.setWarehouseNo(warehouseNo);
            warehouse.init();
            // TODO: 2018/7/9
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
        List<InventoryOnWareHouseDO> list = mapper.selectByCompanyNoAndSkuCode(AppUtil.getLoginUserCompanyNo(),inventoryDO.getSkuCode());
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

    @Override
    public List<InventoryOnWareHouseDO> selectByCompanyNoAndSkuCode(String companyNo, String skuCode) {
        return mapper.selectByCompanyNoAndSkuCode(companyNo, skuCode);
    }

    @Override
    public void order(InventoryDO inventoryDO, Integer quantity) {

        List<InventoryOnWareHouseDO> list = mapper.selectByCompanyNoAndSkuCode(AppUtil.getLoginUserCompanyNo(),inventoryDO.getSkuCode());
        if (list.size()==0){
            throw new ErpCommonException("找不到相关商品库存");
        }
        Map<InventoryOnWareHouseDO, Long> map = chooseWarehouse(list, Long.valueOf(quantity));
        for (InventoryOnWareHouseDO house : map.keySet()) {
            house.setLockedInv(Long.valueOf(quantity));
            mapper.updateByPrimaryKeySelective(house);
        }

    }

    /**Map   Long的意思是需要出的数目*/
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

	@Override
	public InventoryOnWareHouseDO selectByWarehouseNo(String warehouseNo) {
		// TODO Auto-generated method stub
		return mapper.selectByWarehouseNo(warehouseNo);
	}

	@Override
	public InventoryOnWareHouseDO selectByInventoryOnWarehouseNo(String inventoryOnWarehouseNo) {
		// TODO Auto-generated method stub
		return mapper.selectByInventoryOnWarehouseNo(inventoryOnWarehouseNo);
	}
}
