package com.wangqin.globalshop.channelapi.dal;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;

/**
 * Create by 777 on 2018/6/12
 */
public class ChannelSalePriceVo extends ChannelSalePriceDO {

	private String channelItemSkuCode;

	private String channelItemCode;

	public String getChannelItemSkuCode() {
		return channelItemSkuCode;
	}
	public void setChannelItemSkuCode(String channelItemSkuCode) {
		this.channelItemSkuCode = channelItemSkuCode;
	}
	public String getChannelItemCode() {
		return channelItemCode;
	}
	public void setChannelItemCode(String channelItemCode) {
		this.channelItemCode = channelItemCode;
	}
}
