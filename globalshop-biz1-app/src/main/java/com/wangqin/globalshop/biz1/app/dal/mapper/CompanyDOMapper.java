package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;

public interface CompanyDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyDO record);

    int insertSelective(CompanyDO record);

    CompanyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyDO record);

    int updateByPrimaryKeyWithBLOBs(CompanyDO record);

    int updateByPrimaryKey(CompanyDO record);
}