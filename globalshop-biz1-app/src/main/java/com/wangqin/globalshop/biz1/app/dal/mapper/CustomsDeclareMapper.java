package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CustomsDeclare;

public interface CustomsDeclareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomsDeclare record);

    int insertSelective(CustomsDeclare record);

    CustomsDeclare selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomsDeclare record);

    int updateByPrimaryKey(CustomsDeclare record);
}