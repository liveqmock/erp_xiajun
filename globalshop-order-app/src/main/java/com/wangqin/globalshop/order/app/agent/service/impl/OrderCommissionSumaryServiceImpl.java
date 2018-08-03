package com.wangqin.globalshop.order.app.agent.service.impl;

import com.wangqin.globalshop.order.app.agent.service.IOrderCommissionSumaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.AgentOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumaryDOMapperExt;
@Service
public class OrderCommissionSumaryServiceImpl implements IOrderCommissionSumaryService {
@Autowired
private CommissionSumaryDOMapperExt mapperExt;
@Override
public AgentOrderVO queryOrderInfoBySubOrderNo(String subOrderNo, String userNo) {
	return mapperExt.queryOrderInfoBySubOrderNo(subOrderNo,userNo);
}
}
