package com.wangqin.globalshop.inventory.app.service.impl;


import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryInoutDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryInoutVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryInoutMapperExt;

import com.wangqin.globalshop.inventory.app.service.IInventoryInoutService;

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
	private InventoryInoutMapperExt inventoryInoutMapperExt;
	
    @Override
    public Integer queryInventoryInoutCount(InventoryQueryVO inventoryQueryVO) {
        return inventoryInoutMapperExt.queryInventoryInoutCount(inventoryQueryVO);
    }

    @Override
    public List<InventoryInoutDO> queryInventoryInouts(InventoryQueryVO inventoryQueryVO) {
        inventoryQueryVO.init();
       return inventoryInoutMapperExt.queryInventoryInouts(inventoryQueryVO);
    }
}
