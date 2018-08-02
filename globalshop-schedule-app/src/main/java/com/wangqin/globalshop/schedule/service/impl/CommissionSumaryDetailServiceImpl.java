package com.wangqin.globalshop.schedule.service.impl;

import java.util.List;
import java.util.Map;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SumaryDetailQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumaryDetailDOMapperExt;
import com.wangqin.globalshop.schedule.service.CommissionSumaryDetailService;
@Service
public class CommissionSumaryDetailServiceImpl implements CommissionSumaryDetailService{
@Autowired
private CommissionSumaryDetailDOMapperExt mapperExt;
@Override
public Map<String,Object> sumOrderNumSaleCommissionByUserNo(Integer status,String userNo) {
	return mapperExt.sumOrderNumSaleCommissionByUserNo(status, userNo);
}

	public List<Map<String,Object>> sumSettlePageList(SumaryDetailQueryVO queryVO){
		return mapperExt.sumSettlePageList(queryVO);
	}
}
