package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.LogisticCategoryMappingDO;

public interface LogisticCategoryMappingDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LogisticCategoryMappingDO record);

    int insertSelective(LogisticCategoryMappingDO record);

    LogisticCategoryMappingDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LogisticCategoryMappingDO record);

    int updateByPrimaryKey(LogisticCategoryMappingDO record);
}