package com.wangqin.globalshop.channelapi.dal;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/12
 */
public class ChannelListingItemVo extends ChannelListingItemDO {

	//传递上下架原因
	private String node;

	List<ChannelListingItemSkuDO> channelListingItemSkuDOS;


	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public List<ChannelListingItemSkuDO> getChannelListingItemSkuDOS() {
		return channelListingItemSkuDOS;
	}
	public void setChannelListingItemSkuDOS(List<ChannelListingItemSkuDO> channelListingItemSkuDOS) {
		this.channelListingItemSkuDOS = channelListingItemSkuDOS;
	}
}
