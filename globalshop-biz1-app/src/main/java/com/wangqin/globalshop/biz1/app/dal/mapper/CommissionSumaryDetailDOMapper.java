package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CommissionSumaryDetailDO;

public interface CommissionSumaryDetailDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommissionSumaryDetailDO record);

    int insertSelective(CommissionSumaryDetailDO record);

    CommissionSumaryDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommissionSumaryDetailDO record);

    int updateByPrimaryKey(CommissionSumaryDetailDO record);
}