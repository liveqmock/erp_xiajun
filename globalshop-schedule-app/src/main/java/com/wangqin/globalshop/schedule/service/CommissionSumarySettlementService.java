package com.wangqin.globalshop.schedule.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumarySettlementDO;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
public interface CommissionSumarySettlementService {
	List<CommissionSumarySettlementDO> searchByUserNo(String searchByShareId);

	void add(CommissionSumarySettlementDO settlementDO);

	List<CommissionSumarySettlementDO> searchPageList(SettlementQueryVO queryVO);
}
