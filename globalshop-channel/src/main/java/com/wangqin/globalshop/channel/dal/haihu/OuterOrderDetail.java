package com.wangqin.globalshop.channel.dal.haihu;


import java.util.Date;

public class OuterOrderDetail  {

	private Long id;

	private Long outerOrderId;

	private Long skuId;

	private Long itemId;

	private String skuCode;
	private String upc;

	private String itemName;
	private String color;
	private String scale;

	private String skuPic;

	private Integer logisticType;
	private Double freight;
	private Double weight;

	private Double salePrice;

	private String freightStr;

	private String salePriceStr;

	private Integer quantity;


	private Date gmtCreate;

	private Date gmtModify;


	private String  thirdSkuCode;
	

	private Long companyId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOuterOrderId() {
		return outerOrderId;
	}

	public void setOuterOrderId(Long outerOrderId) {
		this.outerOrderId = outerOrderId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}


	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getFreightStr() {
		return freightStr;
	}

	public void setFreightStr(String freightStr) {
		this.freightStr = freightStr;
	}

	public String getSalePriceStr() {
		return salePriceStr;
	}

	public void setSalePriceStr(String salePriceStr) {
		this.salePriceStr = salePriceStr;
	}

	public String getSkuPic() {
		return skuPic;
	}

	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public Integer getLogisticType() {
		return logisticType;
	}

	public void setLogisticType(Integer logisticType) {
		this.logisticType = logisticType;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getThirdSkuCode() {
		return thirdSkuCode;
	}

	public void setThirdSkuCode(String thirdSkuCode) {
		this.thirdSkuCode = thirdSkuCode;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
}
