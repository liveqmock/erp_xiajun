package com.wangqin.globalshop.biz1.app.dal.dataVo;

import java.util.Date;

public class ShippingOrderQueryVO extends PageQueryVO {
	
	private String shippingNo;
	private String logisticNo;
	private String logisticCompany;
	private String erpNo;
	private String receiver;
	private String telephone;
	//状态
	private Integer status;
	private Integer type;
	//发货时间
	private Date startOrderTime;
	private Date endOrderTime;
	/**
	 * 销售ID
	 */
	private Integer salesId;
	
	private String openId;
	
	public String getShippingNo() {
		return shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public String getLogisticNo() {
		return logisticNo;
	}

	public void setLogisticNo(String logisticNo) {
		this.logisticNo = logisticNo;
	}

	public String getLogisticCompany() {
		return logisticCompany;
	}

	public void setLogisticCompany(String logisticCompany) {
		this.logisticCompany = logisticCompany;
	}

	public String getErpNo() {
		return erpNo;
	}

	public void setErpNo(String erpNo) {
		this.erpNo = erpNo;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStartOrderTime() {
		return startOrderTime;
	}

	public void setStartOrderTime(Date startOrderTime) {
		this.startOrderTime = startOrderTime;
	}

	public Date getEndOrderTime() {
		return endOrderTime;
	}

	public void setEndOrderTime(Date endOrderTime) {
		this.endOrderTime = endOrderTime;
	}

	public Integer getSalesId() {
		return salesId;
	}

	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
}
