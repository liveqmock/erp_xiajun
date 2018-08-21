package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelShopDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */
public interface ChannelShopDOMapperExt extends ChannelShopDOMapper {

	public Long searchShopCount(ChannelShopDO channelShopDO);

	public ChannelShopDO searchShop(ChannelShopDO channelShopDO);

	public List<ChannelShopDO> searchShopList(ChannelShopDO channelShopDO);

	public Long gainShopCodeSequence();
}
