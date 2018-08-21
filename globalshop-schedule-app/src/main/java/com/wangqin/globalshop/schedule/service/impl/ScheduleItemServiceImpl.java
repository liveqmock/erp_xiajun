package com.wangqin.globalshop.schedule.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ItemShelfMethod;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;

import com.wangqin.globalshop.schedule.service.ScheduleItemService;

@Service
public class ScheduleItemServiceImpl implements ScheduleItemService{

	@Autowired
	private ItemDOMapperExt itemDOMapperExt;

	

	//定时任务:上架自定义上架时间的商品
	@Override
	public void putOnItemSelfDefineTime() {
		itemDOMapperExt.putOnItemSelfDefineTime(ItemStatus.LISTING.getCode(), ItemShelfMethod.SELF_DEFINE_TIME.getValue(),
				ItemStatus.INIT.getCode());
	}
		
}
