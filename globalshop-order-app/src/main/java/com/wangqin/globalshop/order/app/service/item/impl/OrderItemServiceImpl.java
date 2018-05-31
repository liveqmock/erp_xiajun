package com.wangqin.globalshop.order.app.service.item.impl;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.order.app.service.item.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 */

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private ItemDOMapperExt mapper;
	@Override
	public ItemDO queryItemByItemCode(String itemCode) {
		return mapper.queryItemByItemCode(itemCode);
	}
}
