package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CustomsDeclareDetail;

public interface CustomsDeclareDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomsDeclareDetail record);

    int insertSelective(CustomsDeclareDetail record);

    CustomsDeclareDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomsDeclareDetail record);

    int updateByPrimaryKey(CustomsDeclareDetail record);
}