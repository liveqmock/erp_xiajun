package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.ItemMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.Item;
import com.wangqin.globalshop.biz1.app.service.IItemService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Item 表数据服务层接口实现类
 *
 */
@Service
public class ItemServiceImpl extends SuperServiceImpl<ItemMapper, Item> implements IItemService {


}