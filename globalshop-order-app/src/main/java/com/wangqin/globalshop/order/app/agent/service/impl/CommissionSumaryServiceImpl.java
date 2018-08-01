package com.wangqin.globalshop.order.app.agent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.AgentOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumaryDOMapperExt;
import com.wangqin.globalshop.order.app.agent.service.ICommissionSumaryService;
@Service
public class CommissionSumaryServiceImpl implements ICommissionSumaryService{
@Autowired
private CommissionSumaryDOMapperExt mapperExt;
@Override
public AgentOrderVO queryOrderInfoBySubOrderNo(String subOrderNo) {
	return mapperExt.queryOrderInfoBySubOrderNo(subOrderNo);
}
}
