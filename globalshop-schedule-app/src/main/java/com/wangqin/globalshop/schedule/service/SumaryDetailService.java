package com.wangqin.globalshop.schedule.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.SettlementDetailVo;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SumaryDetailQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDetailDO;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
public interface SumaryDetailService {
	List<SettlementDetailVo> searchPageList(SumaryDetailQueryVO queryVO);

	int deleteByPrimaryKey(Long id);

	int insert(CommissionSumaryDetailDO record);

	int insertSelective(CommissionSumaryDetailDO record);

	CommissionSumaryDetailDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CommissionSumaryDetailDO record);

	int updateByPrimaryKey(CommissionSumaryDetailDO record);
}
