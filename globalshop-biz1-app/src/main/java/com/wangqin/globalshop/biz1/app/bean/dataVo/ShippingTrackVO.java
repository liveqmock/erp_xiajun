package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

public class ShippingTrackVO {
	private Integer status; 
	
	private String info;
	
	private Date gmtCreate;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
}
