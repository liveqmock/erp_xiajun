package com.wangqin.globalshop.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ItemShelfMethod;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.common.enums.ItemIsSale;
import com.wangqin.globalshop.schedule.service.ScheduleItemService;

@Service
public class ScheduleItemServiceImpl implements ScheduleItemService{

	@Autowired
	private ItemDOMapperExt itemDOMapperExt;

	

	//定时任务
	@Override
	public List<ItemDO> queryItemSelfDefineTime() {
		return itemDOMapperExt.queryItemSelfDefineTime(ItemShelfMethod.SELF_DEFINE_TIME.getValue());
	}
		
	//定时任务
	@Override
	public void updateItemIsSaleAndStatus(String itemCode) {				
		itemDOMapperExt.updateItemIsSaleAndStatus(ItemIsSale.SALABLE.getCode().byteValue(), ItemStatus.LISTING.getCode(), itemCode);
	}
}
