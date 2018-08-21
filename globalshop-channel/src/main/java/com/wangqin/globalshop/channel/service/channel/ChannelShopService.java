package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */
public interface ChannelShopService {

	public Long searchShopCount(ChannelShopDO channelShopDO);

	public ChannelShopDO searchShop(ChannelShopDO channelShopDO);

	public List<ChannelShopDO> searchShopList(ChannelShopDO channelShopDO);

	public void createOrUpdate(ChannelShopDO channelShopDO);

	public void checkUnique(ChannelShopDO channelShop);

	public ChannelShopDO addhaihu();

	public void changeOpen(String shopCode, Boolean open);
}
