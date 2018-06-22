package com.wangqin.globalshop.channelapi.dal;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;

/**
 * Create by 777 on 2018/6/15
 */
public class ChannelListingItemSkuVo extends ChannelListingItemSkuDO {

	private Long stockNum;
	public Long getStockNum() {
		return stockNum;
	}
	public void setStockNum(Long stockNum) {
		this.stockNum = stockNum;
	}
}
