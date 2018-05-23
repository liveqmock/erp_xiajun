package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;

public interface ChannelListingItemSkuDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelListingItemSkuDO record);

    int insertSelective(ChannelListingItemSkuDO record);

    ChannelListingItemSkuDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelListingItemSkuDO record);

    int updateByPrimaryKey(ChannelListingItemSkuDO record);
}