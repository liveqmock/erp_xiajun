package com.wangqin.globalshop.biz1.app.dal.dataVo;

import java.util.Date;

public class PurchaseStorageVO  extends PageQueryVO{

	private Long buyerId;
	//订单号
	private String stoOrderNo;
	private Date startTime;
	private Date endTime;
	private Long warehouseId;
	private Integer status;
	private Date putInStart;
	private Date putInEnd;

	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	public String getStoOrderNo() {
		return stoOrderNo;
	}
	public void setStoOrderNo(String stoOrderNo) {
		this.stoOrderNo = stoOrderNo;
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
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPutInStart() {
		return putInStart;
	}
	public void setPutInStart(Date putInStart) {
		this.putInStart = putInStart;
	}
	public Date getPutInEnd() {
		return putInEnd;
	}
	public void setPutInEnd(Date putInEnd) {
		this.putInEnd = putInEnd;
	}
	
	
}
