package com.wangqin.globalshop.schedule.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCommisionApplyDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallCommisionApplyDOMapperExt;
import com.wangqin.globalshop.common.enums.MallCommisionApplyStatus;
import com.wangqin.globalshop.schedule.service.MallCommisionApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
@Service
public class MallCommisionApplyServiceImpl implements MallCommisionApplyService {


	@Autowired
	private MallCommisionApplyDOMapperExt applyDOMapperExt;


	public int deleteByPrimaryKey(Long id){
		return applyDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(MallCommisionApplyDO record){
		return applyDOMapperExt.insert(record);
	}

	public int insertSelective(MallCommisionApplyDO record){
		return applyDOMapperExt.insertSelective(record);
	}

	public MallCommisionApplyDO selectByPrimaryKey(Long id){
		return applyDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(MallCommisionApplyDO record){
		return applyDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(MallCommisionApplyDO record){
		return applyDOMapperExt.updateByPrimaryKey(record);
	}

	public List<MallCommisionApplyDO> selectByStatusAndNotSync(String status){

		   return applyDOMapperExt.selectByStatusAndNotSync(status);

	}


}
