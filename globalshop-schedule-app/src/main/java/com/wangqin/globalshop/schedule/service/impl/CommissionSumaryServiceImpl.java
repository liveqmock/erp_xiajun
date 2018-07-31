package com.wangqin.globalshop.schedule.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CommissionSumaryDOMapperExt;
import com.wangqin.globalshop.schedule.service.CommissionSumaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
@Service
public class CommissionSumaryServiceImpl implements CommissionSumaryService {

	@Autowired
	private CommissionSumaryDOMapperExt commissionSumaryDOMapperExt;

	public int deleteByPrimaryKey(Long id){
		return commissionSumaryDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(CommissionSumaryDO record){
		return commissionSumaryDOMapperExt.insert(record);
	}

	public int insertSelective(CommissionSumaryDO record){
		return commissionSumaryDOMapperExt.insertSelective(record);
	}

	public CommissionSumaryDO selectByPrimaryKey(Long id){
		return commissionSumaryDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(CommissionSumaryDO record){
		return commissionSumaryDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(CommissionSumaryDO record){
		return commissionSumaryDOMapperExt.updateByPrimaryKey(record);
	}
	public List<CommissionSumaryDO> selectMorethan15Day() {
		return commissionSumaryDOMapperExt.selectMorethan15Day();
	}
}
