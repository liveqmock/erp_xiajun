package com.wangqin.globalshop.order.app.agent.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CommissionQueryVO;

public interface IOrderCommissionSumaryDetailService {


	Double sumSettlementAbleByUserNo(String userNo,Integer status);
	
	List<String> querySubOrderNoListByUserNo(CommissionQueryVO qv);
}
