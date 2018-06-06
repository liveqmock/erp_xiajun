package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemCategoryScaleMapperExt;
import com.wangqin.globalshop.item.app.service.IItemCategoryScaleService;

@Service
public class ItemCategoryScaleServiceImpl implements IItemCategoryScaleService{

	@Autowired
	private ItemCategoryScaleMapperExt itemCategoryScaleMapperExt;
	
	@Override
	public List<ItemCategoryScaleDO> selectCategoryScaleByCategoryCode(String categoryCode) {
		return itemCategoryScaleMapperExt.selectCategoryScaleByCategoryCode(categoryCode);
	}
	
	
}
