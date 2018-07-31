package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDO;

public interface CommissionSumaryDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommissionSumaryDO record);

    int insertSelective(CommissionSumaryDO record);

    CommissionSumaryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommissionSumaryDO record);

    int updateByPrimaryKey(CommissionSumaryDO record);
}