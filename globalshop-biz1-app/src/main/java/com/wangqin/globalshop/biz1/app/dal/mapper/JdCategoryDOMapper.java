package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdCategoryDO;

public interface JdCategoryDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JdCategoryDO record);

    int insertSelective(JdCategoryDO record);

    JdCategoryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JdCategoryDO record);

    int updateByPrimaryKey(JdCategoryDO record);
}