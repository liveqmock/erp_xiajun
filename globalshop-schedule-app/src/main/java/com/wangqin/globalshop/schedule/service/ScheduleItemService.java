package com.wangqin.globalshop.schedule.service;

import java.util.List;



import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;

public interface ScheduleItemService {

	//定时任务
	List<ItemDO> queryItemSelfDefineTime();
		
	//定时任务
	void updateItemIsSaleAndStatus(String itemCode);
}
