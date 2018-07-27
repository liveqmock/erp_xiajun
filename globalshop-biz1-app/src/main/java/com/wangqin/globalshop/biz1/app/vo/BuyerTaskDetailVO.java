package com.wangqin.globalshop.biz1.app.vo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDetailDO;

/**
 * Create by 777 on 2018/7/25
 */
public class BuyerTaskDetailVO extends BuyerTaskDetailDO {
	private Long buyerId;
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
}
