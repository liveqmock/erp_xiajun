package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.customsDeclareDetail;

public interface customsDeclareDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(customsDeclareDetail record);

    int insertSelective(customsDeclareDetail record);

    customsDeclareDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(customsDeclareDetail record);

    int updateByPrimaryKey(customsDeclareDetail record);
}