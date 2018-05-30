package com.wangqin.globalshop.channel.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelListingItemSkuDOMapper;

/**
 * Create by 777 on 2018/5/28
 */
public interface ChannelListingItemSkuDOMapperExt extends ChannelListingItemSkuDOMapper {

	public ChannelListingItemSkuDO queryPo(ChannelListingItemSkuDO so);

}
