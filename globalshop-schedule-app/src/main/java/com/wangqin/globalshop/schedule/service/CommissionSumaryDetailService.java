package com.wangqin.globalshop.schedule.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CommissionSumaryDetailService {
	Map<String,Object> sumOrderNumSaleCommissionByUserNo(Integer status,String userNo);

	List<Map<String,Object>> sumSettlePageList(Integer status, String companyNo);
}
