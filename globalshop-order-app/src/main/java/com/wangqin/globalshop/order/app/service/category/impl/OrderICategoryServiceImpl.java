package com.wangqin.globalshop.order.app.service.category.impl;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemCategoryMapperExt;
import com.wangqin.globalshop.order.app.service.category.OrderICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderICategoryServiceImpl implements OrderICategoryService {
	@Autowired
	private ItemCategoryMapperExt mapper;
	@Override
	public ItemCategoryDO selectByCategoryCode(String categoryCode) {
		return mapper.queryByCategoryCode(categoryCode);
	}
}
