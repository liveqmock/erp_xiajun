package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

/**
 * 订单查询
 * @author zhulu
 *
 */
public class OuterOrderQueryVO extends PageQueryVO{
	/**
	 * 销售名称
	 */
	private String salesName;
	
	private Integer salesId;
	
	private String targetNo;
	
	private String orderNo;
	
	/**
	 * 订单开始时间
	 */
	private Date startGmtCreate;
	
	/**
	 * 订单结束时间
	 */
	private Date endGmtCreate;
	
	/**
	 * 状态
	 */
	private Integer status;// 订单状态
	
	private String receiver; //收货人
	
	private String telephone;//电话
	
	/**
	 * 备货状态
	 */
	private Integer stockStatus;// 备货状态
	
	private String upc;			//sku upc
	
	private String itemName;	//sku 名称
	
	private String skuCode;		//sku code
	
	private String openId;		//open_id
	
	private String companyNo;
	
	public Integer getSalesId() {
		return salesId;
	}
	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getTargetNo() {
		return targetNo;
	}
	public void setTargetNo(String targetNo) {
		this.targetNo = targetNo;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
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
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyId) {
		this.companyNo = companyId;
	}
}
