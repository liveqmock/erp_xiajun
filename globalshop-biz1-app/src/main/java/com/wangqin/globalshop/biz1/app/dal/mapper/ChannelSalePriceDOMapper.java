package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;

public interface ChannelSalePriceDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelSalePriceDO record);

    int insertSelective(ChannelSalePriceDO record);

    ChannelSalePriceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelSalePriceDO record);

    int updateByPrimaryKey(ChannelSalePriceDO record);
}