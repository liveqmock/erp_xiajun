package com.wangqin.globalshop.schedule.service.impl;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumarySettlementDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CommissionSumarySettlementDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumarySettlementDOMapperExt;
import com.wangqin.globalshop.schedule.service.CommissionSumarySettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */


@Service
public class CommissionSumarySettlementServiceImpl implements CommissionSumarySettlementService {

	@Autowired
	private CommissionSumarySettlementDOMapperExt settlementDOMapperExt;

	public List<CommissionSumarySettlementDO> searchByUserNo(String searchByShareId){

		return settlementDOMapperExt.searchByUserNo(searchByShareId);
	}

	public void add(CommissionSumarySettlementDO settlementDO){

		//todo 需要校验一些字段的合法性
		settlementDOMapperExt.insert(settlementDO);

	}

	public List<CommissionSumarySettlementDO> searchPageList(SettlementQueryVO queryVO){
           List<CommissionSumarySettlementDO> resultList = new ArrayList<>();
		   List<CommissionSumarySettlementDO> pageList = settlementDOMapperExt.searchPageList(queryVO);
		   resultList.addAll(pageList);
		   return resultList;
	}


}
