package com.wangqin.globalshop.inventory.app.service.impl;


import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryInoutDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryInoutMapperExt;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.WarehouseDOMapperExt;
import com.wangqin.globalshop.inventory.app.service.IInventoryInoutService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author biscuit
 * @data 2018/06/01
 */
@Service
public class InventoryInoutServiceImpl implements IInventoryInoutService {
    @Autowired
	private WarehouseDOMapperExt warehouseDOMapper;

	@Autowired
	private InventoryInoutMapperExt inventoryInoutMapper;
	
    @Override
    public Integer queryInventoryInoutCount(InventoryQueryVO inventoryQueryVO) {
        return inventoryInoutMapper.queryInventoryInoutCount(inventoryQueryVO);
    }

    @Override
    public List<InventoryInoutDO> queryInventoryInouts(InventoryQueryVO inventoryQueryVO) {
        inventoryQueryVO.init();
       return inventoryInoutMapper.queryInventoryInouts(inventoryQueryVO);
    }

	@Override
	public List<InventoryQueryVO> queryInventoryInoutsVo(InventoryQueryVO inventoryQueryVO) {
		// TODO Auto-generated method stub
		inventoryQueryVO.init();
		return inventoryInoutMapper.queryInventoryInoutsVo(inventoryQueryVO);
	}

    @Override
    public List<InventoryInoutItemVO> listInventoryInout(InventoryInoutQueryVO inventoryInoutQueryVO, PageQueryParam pageQueryParam) {
        pageQueryParam.calculateRowIndex();
        return inventoryInoutMapper.listInventoryInout(inventoryInoutQueryVO, pageQueryParam);
    }

    @Override
    public int countInventoryInout(InventoryInoutQueryVO inventoryInoutQueryVO) {
        return inventoryInoutMapper.countInventoryInout(inventoryInoutQueryVO);
    }
}
