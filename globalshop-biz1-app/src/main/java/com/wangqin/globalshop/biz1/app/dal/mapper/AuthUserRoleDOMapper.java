package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;

public interface AuthUserRoleDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthUserRoleDO record);

    int insertSelective(AuthUserRoleDO record);

    AuthUserRoleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthUserRoleDO record);

    int updateByPrimaryKey(AuthUserRoleDO record);
}