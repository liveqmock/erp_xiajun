package com.wangqin.globalshop.channelapi.dal;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/12
 */
public class ChannelListingItemVo extends ChannelListingItemDO {

	//传递上下架原因
	private String node;

	List<ChannelListingItemSkuVo> channelListingItemSkuVos;


	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public List<ChannelListingItemSkuVo> getChannelListingItemSkuVos() {
		return channelListingItemSkuVos;
	}
	public void setChannelListingItemSkuVos(List<ChannelListingItemSkuVo> channelListingItemSkuVos) {
		this.channelListingItemSkuVos = channelListingItemSkuVos;
	}
}
