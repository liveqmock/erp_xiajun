package com.wangqin.globalshop.biz1.app.bean.dto;

import com.wangqin.globalshop.common.utils.PageQueryVO;

public class TaskDailyDTO extends  PageQueryVO{
	
	private Long wxUserId;

	private Long buyerId;
	
	public Long getWxUserId() {
		return wxUserId;
	}

	public void setWxUserId(Long wxUserId) {
		this.wxUserId = wxUserId;
		this.buyerId = wxUserId;
	}

	public Long getBuyerId() {
		return buyerId;
	}


	
}
