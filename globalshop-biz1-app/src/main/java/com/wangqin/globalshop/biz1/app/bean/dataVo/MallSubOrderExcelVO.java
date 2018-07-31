package com.wangqin.globalshop.biz1.app.bean.dataVo;


/**
 * 导出子订单
 * apex公司专用
 * 这里的字段在好几个表里面， 并不是mall_sub_order表一个
 * @author xiajun
 *
 */
public class MallSubOrderExcelVO {
	
	private String orderNo;//主订单号,mall_sub_order表
	
    private Double weight;//净重,mall_sub_order表
    
    private Double gross;//毛重
    
    private Integer quantity;//数量 ,mall_sub_order表
	
    private Double salePrice;//价格,mall_sub_order表
    
    private String upc;//upc,mall_sub_order表
    
    private String itemName;//商品名称,mall_sub_order表
    
    private String brandName;//品牌名字
    
    private String receiver;//收件人姓名,mall_sub_order表

    private String receiverState;//省,mall_sub_order表

    private String receiverCity;//市,mall_sub_order表

    private String receiverDistrict;//区,mall_sub_order表

    private String receiverAddress;//地址,mall_sub_order表
    
    private String telephone;//电话,mall_sub_order表
    
    private String idCard;//收件人证件,mall_sub_order表
    
    private String senderName;//发货人名字
    
    private String senderAddress;//发货人地址
    
    private String senderPhone;//发货人电话
    
    private String scaleCollection;//规格
    
    private String skuCode;//这个是为了查询品牌的名字以及规格
    
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getGross() {
		return gross;
	}

	public void setGross(Double gross) {
		this.gross = gross;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getScaleCollection() {
		return scaleCollection;
	}

	public void setScaleCollection(String scaleCollection) {
		this.scaleCollection = scaleCollection;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	
	
	
}
    
    