package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCategoryMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategory;
import com.wangqin.globalshop.biz1.app.service.IItemCategoryService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * ItemCategory 表数据服务层接口实现类
 *
 */
@Service
public class ItemCategoryServiceImpl extends SuperServiceImpl<ItemCategoryMapper, ItemCategory> implements IItemCategoryService {


}