package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemBrandDOMapper;
import com.wangqin.globalshop.biz1.app.service.IItemBrandService;




@Service("brandService")
public class ItemBrandServiceImpl extends SuperServiceImpl<ItemBrandDOMapper, ItemBrandDO> implements IItemBrandService {

	@Autowired
	private ItemBrandDOMapper itemBrandDOMapper;
	
	@Override
	public void add(ItemBrandDO itemBrand) {
		
		itemBrandDOMapper.insert(itemBrand);
	}
}
