package com.wangqin.globalshop.item.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.item.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.item.app.dal.mapperExt.ItemBrandDOMapperExt;
import com.wangqin.globalshop.item.app.service.IItemBrandService;




@Service
public class ItemBrandServiceImpl implements IItemBrandService {

	@Autowired
	private ItemBrandDOMapperExt itemBrandDOMapperExt;
	
	@Override
	public void add(ItemBrandDO itemBrand) {
		
		//itemBrandDOMapperExt.insert(itemBrand);
	}
}
