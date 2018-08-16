package com.wangqin.globalshop.item.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IItemReturnOrderService {

	//首页数据看板：未完成售后订单数
	Integer sumReturningOrderNum(String companyNo);
}
