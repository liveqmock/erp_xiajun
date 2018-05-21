package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthOrganizationDO;

public interface AuthOrganizationDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthOrganizationDO record);

    int insertSelective(AuthOrganizationDO record);

    AuthOrganizationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthOrganizationDO record);

    int updateByPrimaryKey(AuthOrganizationDO record);
}