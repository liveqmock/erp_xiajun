package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.customsDeclare;

public interface customsDeclareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(customsDeclare record);

    int insertSelective(customsDeclare record);

    customsDeclare selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(customsDeclare record);

    int updateByPrimaryKey(customsDeclare record);
}