package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.Inventory;
import com.wangqin.globalshop.biz1.app.service.IInventoryService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Inventory 表数据服务层接口实现类
 *
 */
@Service
public class InventoryServiceImpl extends SuperServiceImpl<InventoryMapper, Inventory> implements IInventoryService {


}