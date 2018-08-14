package com.wangqin.globalshop.item.app.service;

public interface IItemShippingOrderService {

	//首页数据看板：今日已发货包裹数
	Integer sumTodySentNum(String companyNo);
	
	//首页数据看板：一周内已发货包裹数
	Integer sumWeekSentNum(String companyNo);
	
}
