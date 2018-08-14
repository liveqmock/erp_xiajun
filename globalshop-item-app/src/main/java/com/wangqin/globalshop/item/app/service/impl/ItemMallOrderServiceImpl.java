package com.wangqin.globalshop.item.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallOrderMapperExt;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.item.app.service.IItemMallOrderService;

@Service
public class ItemMallOrderServiceImpl implements IItemMallOrderService{

	@Autowired
	private MallOrderMapperExt mapperExt;
	
	//首页数据看板：计算今日订单数（已付款订单数）
	@Override
	public Integer sumPaidOrderNumByDate(Integer dayIndex,String companyNo) {
		return mapperExt.sumPaidOrderNumByDate(OrderStatus.paidList(), dayIndex, companyNo);
	}
		
	//首页数据看板：待发货订单数
	@Override
	public Integer sumWaitSendOrderNum(String companyNo) {
		return mapperExt.sumWaitSendOrderNum(OrderStatus.waitSendList(), companyNo);
	}
		
	//首页数据看板：未完成售后订单数
	@Override
	public Integer sumReturningOrderNum(String companyNo) {
		return mapperExt.sumReturningOrderNum(OrderStatus.RETURNING.getCode(), companyNo);
	}

}
