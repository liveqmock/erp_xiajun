package com.wangqin.globalshop.schedule.service;

import java.util.List;
import java.util.Map;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SumaryDetailQueryVO;
import org.apache.ibatis.annotations.Param;

public interface CommissionSumaryDetailService {
	Map<String,Object> sumOrderNumSaleCommissionByUserNo(Integer status,String userNo);

	List<Map<String,Object>> sumSettlePageList(SumaryDetailQueryVO queryVO);
}
