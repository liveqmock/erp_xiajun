package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemSkuDOMapper;

public interface ChannelListingItemSkuDOMapperExt extends ChannelListingItemSkuDOMapper {
    ChannelListingItemSkuDO queryPo(ChannelListingItemSkuDO so);
}