package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.LogisticCompanyDO;

public interface LogisticCompanyDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LogisticCompanyDO record);

    int insertSelective(LogisticCompanyDO record);

    LogisticCompanyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LogisticCompanyDO record);

    int updateByPrimaryKey(LogisticCompanyDO record);
}