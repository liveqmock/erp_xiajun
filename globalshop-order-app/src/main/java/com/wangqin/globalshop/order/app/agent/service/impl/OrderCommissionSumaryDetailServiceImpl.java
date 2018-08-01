package com.wangqin.globalshop.order.app.agent.service.impl;

import java.util.List;

import com.wangqin.globalshop.order.app.agent.service.IOrderCommissionSumaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CommissionQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumaryDetailDOMapperExt;
import com.wangqin.globalshop.common.enums.CommissionStatus;
@Service
public class OrderCommissionSumaryDetailServiceImpl implements IOrderCommissionSumaryDetailService {

	@Autowired
	private CommissionSumaryDetailDOMapperExt mapperExt;
	@Override
	public Double sumSettlementAbleByUserNo(String userNo) {
		return mapperExt.sumSettlementAbleByUserNo(userNo, CommissionStatus.BALANCE_ABLE.getValue());
	}
	@Override
	public List<String> querySubOrderNoListByUserNo(CommissionQueryVO qv) {
		return mapperExt.querySubOrderNoListByUserNo(qv);
	}

}
