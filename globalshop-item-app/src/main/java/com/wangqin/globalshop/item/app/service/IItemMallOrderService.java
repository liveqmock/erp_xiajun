package com.wangqin.globalshop.item.app.service;


public interface IItemMallOrderService {

	//首页数据看板：计算今日订单数（已付款订单数）
	Integer sumPaidOrderNumByDate(Integer dayIndex,String companyNo); 
	
	//首页数据看板：计算一周订单数（已付款订单数，减去已退款订单）
	Integer sumWeekPaidOrder(String companyNo); 
	
	//首页数据看板：待发货订单数
	Integer sumWaitSendOrderNum(String companyNo); 
	
	//首页数据看板：未完成售后订单数
	Integer sumReturningOrderNum(String companyNo); 
}
