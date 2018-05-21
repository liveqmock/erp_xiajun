package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;

public interface AuthResourceDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthResourceDO record);

    int insertSelective(AuthResourceDO record);

    AuthResourceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthResourceDO record);

    int updateByPrimaryKey(AuthResourceDO record);
}