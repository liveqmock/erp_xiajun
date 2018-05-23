package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;

public interface AuthUserDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthUserDO record);

    int insertSelective(AuthUserDO record);

    AuthUserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthUserDO record);

    int updateByPrimaryKey(AuthUserDO record);
}