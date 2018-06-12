package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ScaleTypeDO;

public interface ScaleTypeDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScaleTypeDO record);

    int insertSelective(ScaleTypeDO record);

    ScaleTypeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScaleTypeDO record);

    int updateByPrimaryKey(ScaleTypeDO record);
}