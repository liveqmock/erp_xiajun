package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.ItemFindMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemFind;
import com.wangqin.globalshop.biz1.app.service.IItemFindService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * ItemFind 表数据服务层接口实现类
 *
 */
@Service
public class ItemFindServiceImpl extends SuperServiceImpl<ItemFindMapper, ItemFind> implements IItemFindService {


}