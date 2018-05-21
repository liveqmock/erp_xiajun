package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxSpecialPageDO;

public interface MallWxSpecialPageDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallWxSpecialPageDO record);

    int insertSelective(MallWxSpecialPageDO record);

    MallWxSpecialPageDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallWxSpecialPageDO record);

    int updateByPrimaryKey(MallWxSpecialPageDO record);
}