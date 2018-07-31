package com.wangqin.globalshop.schedule.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCommisionApplyDO;
import com.wangqin.globalshop.common.enums.MallCommisionApplyStatus;

import java.util.List;

/**
 * Create by 777 on 2018/7/31
 */
public interface MallCommisionApplyService {
	List<MallCommisionApplyDO> selectByStatusAndNotSync(String code);

	int deleteByPrimaryKey(Long id);

	int insert(MallCommisionApplyDO record);

	int insertSelective(MallCommisionApplyDO record);

	MallCommisionApplyDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MallCommisionApplyDO record);

	int updateByPrimaryKey(MallCommisionApplyDO record);
}
