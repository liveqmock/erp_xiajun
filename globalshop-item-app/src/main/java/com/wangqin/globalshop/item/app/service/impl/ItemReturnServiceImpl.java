package com.wangqin.globalshop.item.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallReturnOrderDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.OrderReturnStatus;
import com.wangqin.globalshop.item.app.service.IItemReturnOrderService;

@Service
public class ItemReturnServiceImpl implements IItemReturnOrderService{

	@Autowired
	private MallReturnOrderDOMapperExt mapperExt;
	
	@Override
	public Integer sumReturningOrderNum(String companyNo) {
		return mapperExt.sumReturningOrderNum(companyNo, OrderReturnStatus.returningOrderStatus());
	}
}
