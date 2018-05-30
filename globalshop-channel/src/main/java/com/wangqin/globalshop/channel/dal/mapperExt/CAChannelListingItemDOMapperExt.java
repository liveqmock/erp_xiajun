package com.wangqin.globalshop.channel.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemDOMapper;

/**
 * Create by 777 on 2018/5/28
 */
public interface CAChannelListingItemDOMapperExt extends ChannelListingItemDOMapper {

	public ChannelListingItemDO queryPo(ChannelListingItemDO so);
}
