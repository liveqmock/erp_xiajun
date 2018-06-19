package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdCategoryAttarValueDO;

public interface JdCategoryAttarValueDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdCategoryAttarValueDO record);

    int insertSelective(JdCategoryAttarValueDO record);

    JdCategoryAttarValueDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdCategoryAttarValueDO record);

    int updateByPrimaryKey(JdCategoryAttarValueDO record);
}