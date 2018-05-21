package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;

public interface AuthRoleResourceDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleResourceDO record);

    int insertSelective(AuthRoleResourceDO record);

    AuthRoleResourceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthRoleResourceDO record);

    int updateByPrimaryKey(AuthRoleResourceDO record);
}