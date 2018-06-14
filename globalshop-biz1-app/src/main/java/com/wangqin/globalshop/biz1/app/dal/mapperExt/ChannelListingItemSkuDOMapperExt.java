package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemSkuDOMapper;

import java.util.List;

public interface ChannelListingItemSkuDOMapperExt extends ChannelListingItemSkuDOMapper {

    ChannelListingItemSkuDO queryPo(ChannelListingItemSkuDO so);

    List<ChannelListingItemSkuDO> queryPoList(ChannelListingItemSkuDO so);
}
