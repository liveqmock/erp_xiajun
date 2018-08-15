package com.wangqin.globalshop.item.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IItemMallOrderService {


	//首页数据看板：计算今日订单数（已付款订单数）
	Integer sumPaidOrderNumByDate(Integer dayIndex,String companyNo); 
	
	//首页数据看板：待发货订单数
	Integer sumWaitSendOrderNum(String companyNo); 
	
	//首页数据看板：未完成售后订单数
	Integer sumReturningOrderNum(String companyNo); 
}
