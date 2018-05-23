package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;

public interface AuthRoleDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleDO record);

    int insertSelective(AuthRoleDO record);

    AuthRoleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthRoleDO record);

    int updateByPrimaryKey(AuthRoleDO record);
}