package com.wangqin.globalshop.item.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingOrderDOMapperExt;
import com.wangqin.globalshop.item.app.service.IItemShippingOrderService;

@Service
public class ItemShippingOrderServiceImpl implements IItemShippingOrderService{

	@Autowired
	private ShippingOrderDOMapperExt mapperExt;
	
	//首页数据看板：今日已发货包裹数
	@Override
	public Integer sumTodySentNum(String companyNo) {
		return mapperExt.sumTodySentNum(companyNo);
	}
		
	//首页数据看板：一周内已发货包裹数
	@Override
	public Integer sumWeekSentNum(String companyNo) {
		return mapperExt.sumWeekSentNum(companyNo);
	}
}
