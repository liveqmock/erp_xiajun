package com.wangqin.globalshop.schedule.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDO;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
public interface CommissionSumaryService {

	int deleteByPrimaryKey(Long id);

	int insert(CommissionSumaryDO record);

	int insertSelective(CommissionSumaryDO record);

	CommissionSumaryDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CommissionSumaryDO record);

	int updateByPrimaryKey(CommissionSumaryDO record);

	public List<CommissionSumaryDO> selectMorethan15Day();

}
