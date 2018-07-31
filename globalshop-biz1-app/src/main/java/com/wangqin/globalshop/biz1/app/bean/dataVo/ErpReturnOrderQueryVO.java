package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

public class ErpReturnOrderQueryVO extends PageQueryVO {
	private String orderNo;
	
	private String erpNo;
	
	private String upc;
	
	private String skuCode;
	
	private String itemName;
	
	private String returnReason;	
	
	private Integer status;
	
	private Integer returnQuantity;
	
	private Integer returnPrice;	
	
	private String salesName;
	
	private Date startGmtCreate;
	
	private Date endGmtCreate;		
	
	private Integer returnType;
	
	private Integer returnRefer;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getErpNo() {
		return erpNo;
	}
	public void setErpNo(String erpNo) {
		this.erpNo = erpNo;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getStartGmtCreate() {
		return startGmtCreate;
	}
	public void setStartGmtCreate(Date startGmtCreate) {
		this.startGmtCreate = startGmtCreate;
	}
	public Date getEndGmtCreate() {
		return endGmtCreate;
	}
	public void setEndGmtCreate(Date endGmtCreate) {
		this.endGmtCreate = endGmtCreate;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	
	public Integer getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(Integer returnQuantity) {
		this.returnQuantity = returnQuantity;
	}
	public Integer getReturnPrice() {
		return returnPrice;
	}
	public void setReturnPrice(Integer returnPrice) {
		this.returnPrice = returnPrice;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	public Integer getReturnType() {
		return returnType;
	}
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}
	public Integer getReturnRefer() {
		return returnRefer;
	}
	public void setReturnRefer(Integer returnRefer) {
		this.returnRefer = returnRefer;
	}
	
}
