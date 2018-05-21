package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxBannerDO;

public interface MallWxBannerDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallWxBannerDO record);

    int insertSelective(MallWxBannerDO record);

    MallWxBannerDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallWxBannerDO record);

    int updateByPrimaryKey(MallWxBannerDO record);
}