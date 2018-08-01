package com.wangqin.globalshop.order.app.agent.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CommissionQueryVO;

public interface ICommissionSumaryDetailService {


	Double sumSettlementAbleByUserNo(String userNo);
	
	List<String> querySubOrderNoListByUserNo(CommissionQueryVO qv);
}
