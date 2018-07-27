package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

public class InventoryOutVO extends PageQueryVO {
	private Long warehouseId;
	
	private String invOutNo;
	
	/**
	 * 创建时间开始
	 */
	private Date startGmt;
	/**
	 * 创建时间end
	 */
	private Date endGmt;
	
	
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getInvOutNo() {
		return invOutNo;
	}
	public void setInvOutNo(String invOutNo) {
		this.invOutNo = invOutNo;
	}
	public Date getStartGmt() {
		return startGmt;
	}
	public void setStartGmt(Date startGmt) {
		this.startGmt = startGmt;
	}
	public Date getEndGmt() {
		return endGmt;
	}
	public void setEndGmt(Date endGmt) {
		this.endGmt = endGmt;
	}
	
	
}
