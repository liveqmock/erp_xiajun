package com.wangqin.globalshop.order.app.service.mall.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallCommisionApplyDOMapperExt;
import com.wangqin.globalshop.common.enums.MallCommisionApplyStatus;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.order.app.service.mall.IOrderMallCommisionApplyService;

@Service
public class OrderMallCommisionApplyServiceImpl implements IOrderMallCommisionApplyService{
	@Autowired
	private MallCommisionApplyDOMapperExt mapperExt;
	
	@Override
	public void updateStatusBySubOrderNo(String subOrderNo) {
		Date currentDate = new Date();
		String currentTime = DateUtil.transferDateToString(currentDate);
		mapperExt.updateStatusBySubOrderNo(MallCommisionApplyStatus.RECEIVE.getCode(),
				subOrderNo, currentDate, currentTime);
	}
}
