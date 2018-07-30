package com.wangqin.globalshop.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.schedule.service.ScheduleItemService;

@Service
public class ScheduleItemServiceImpl implements ScheduleItemService{

	@Autowired
	private ItemDOMapperExt itemDOMapperExt;
	
	@Override
	public List<ItemDO> queryItemSalable(Byte isSale) {
		return itemDOMapperExt.queryItemSalable(isSale);
	}
	
	@Override
	public void updateItemIsSale(Byte isSale,String itemCode) {
		itemDOMapperExt.updateItemIsSale(isSale, itemCode);
	}
}
