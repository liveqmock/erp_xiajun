package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemDOMapper;

public interface ChannelListingItemDOMapperExt extends ChannelListingItemDOMapper {
    ChannelListingItemDO queryPo(ChannelListingItemDO so);
}