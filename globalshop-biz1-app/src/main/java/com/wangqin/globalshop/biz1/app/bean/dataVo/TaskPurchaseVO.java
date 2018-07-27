package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

/**
 * 采购任务流水查询条件
 *
 */
public class TaskPurchaseVO {

	private Long buyerId;
	private Integer status;
	private Date startTime;
	private Date endTime;
	
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
