package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ScaleDO;

public interface ScaleDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScaleDO record);

    int insertSelective(ScaleDO record);

    ScaleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScaleDO record);

    int updateByPrimaryKey(ScaleDO record);
}