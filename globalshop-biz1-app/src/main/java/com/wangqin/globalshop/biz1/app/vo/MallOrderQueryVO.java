package com.wangqin.globalshop.biz1.app.vo;

import java.util.Date;

/**
 * ErpOrder查询
 * 
 * @author liuhui
 *
 */
public class MallOrderQueryVO extends PageQueryVO {
	/**
	 * 状态
	 */
	private Integer status;// 订单状态
	/**
	 * 备货状态
	 */
	private Integer stockStatus;// 备货状态
	/**
	 * 外部订单id
	 */
	private Long outerOrderId;
	/**
	 * 外部订单的订单号
	 */
	private String targetNo;
	/**
	 * 外部订单的内部订单
	 */
	private String orderNo;
	/**
	 * 内部订单号
	 */
	private String erpNo;
	/**
	 * 条形码
	 */
	private String upc;
	/**
	 * sku_code
	 */
	private String skuCode;
	/**
	 * 商品名称
	 */
	private String itemName;
	/**
	 * 收件人
	 */
	private String receiver;
	/**
	 * 销售员
	 */
	private String salesName;
	/**
	 * 销售ID
	 */
	private Integer salesId;
	/**
	 * 物流类型
	 */
	private String logisticType;
	/**
	 * 配货仓库
	 */
	private Integer warehouseId;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 订单开始时间
	 */
	private Date startGmtCreate;
	
	/**
	 * 订单结束时间
	 */
	private Date endGmtCreate;
	
	private String closeReason;
	/**
	 * 小程序用户openid
	 */
	private String openId;
	
	private String idCard;
	
	private String companyNO;

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

	public Long getOuterOrderId() {
		return outerOrderId;
	}

	public void setOuterOrderId(Long outerOrderId) {
		this.outerOrderId = outerOrderId;
	}

	public String getTargetNo() {
		return targetNo;
	}

	public void setTargetNo(String targetNo) {
		this.targetNo = targetNo;
	}

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

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	
	public Integer getSalesId() {
		return salesId;
	}

	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}

	public String getLogisticType() {
		return logisticType;
	}

	public void setLogisticType(String logisticType) {
		this.logisticType = logisticType;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCompanyNo() {
		return companyNO;
	}

	public void setCompanyNo(String companyId) {
		this.companyNO = companyId;
	}
	
}
