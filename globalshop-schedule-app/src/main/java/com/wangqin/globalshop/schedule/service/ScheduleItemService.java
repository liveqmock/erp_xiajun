package com.wangqin.globalshop.schedule.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;

public interface ScheduleItemService {

	List<ItemDO> queryItemSalable(Byte isSale);
	
	void updateItemIsSale(Byte isSale,String itemCode);
	
	void putOffItemByItemCode(String itemCode);
}
