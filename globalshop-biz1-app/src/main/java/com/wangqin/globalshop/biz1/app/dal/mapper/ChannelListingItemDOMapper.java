package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;

public interface ChannelListingItemDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelListingItemDO record);

    int insertSelective(ChannelListingItemDO record);

    ChannelListingItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelListingItemDO record);

    int updateByPrimaryKey(ChannelListingItemDO record);
}