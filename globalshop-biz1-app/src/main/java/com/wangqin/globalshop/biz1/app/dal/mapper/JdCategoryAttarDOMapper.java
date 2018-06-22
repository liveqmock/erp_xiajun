package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdCategoryAttarDO;

public interface JdCategoryAttarDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdCategoryAttarDO record);

    int insertSelective(JdCategoryAttarDO record);

    JdCategoryAttarDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdCategoryAttarDO record);

    int updateByPrimaryKey(JdCategoryAttarDO record);
}