package com.wangqin.globalshop.biz1.app.vo;

import java.util.Date;

/**
 * 商品对象查询
 * @author zhulu
 *
 */
public class ItemSkuQueryVO extends PageQueryVO{

	private Long id;
	
	private Long itemId;
	
	private Long lockedVirtualInv;
	/**
	 * 商品编码
	 */
	private String itemCode;
	
	/**
	 * SKU编码
	 */
	private String skuCode;
	
	/**
	 * upc 条码
	 */
	private String upc;
	
	/**
	 * 商品名称
	 */
	private String itemName;
	
	
	/**
	 * 英文名称
	 */
	private String enName;
	
	/**
	 * 商品类目ID
	 * 
	 */
	private Long categoryId;
	
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 颜色
	 */
	private String color;
	
	/**
	 * 创建时间开始
	 */
	private Date startGmt;
	/**
	 * 创建时间end
	 */
	private Date endGmt;
	
	/**
	 * 创建时间开始
	 */
	private Date startOrderTime;
	/**
	 * 创建时间end
	 */
	private Date endOrderTime;
	
	/**
	 * 是否是采购单查询商品列表, 1是
	 */
	private Integer isPurchase;
	/**
	 * 当是采购单查询商品列表时, 是否按订单搜索, 1是
	 */
	private Integer isOrderQuery;
	
	private String buySite;
	
	private Long companyId;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}



	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public Integer getIsPurchase() {
		return isPurchase;
	}

	public void setIsPurchase(Integer isPurchase) {
		this.isPurchase = isPurchase;
	}

	public Integer getIsOrderQuery() {
		return isOrderQuery;
	}

	public void setIsOrderQuery(Integer isOrderQuery) {
		this.isOrderQuery = isOrderQuery;
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

	public String getBuySite() {
		return buySite;
	}

	public void setBuySite(String buySite) {
		this.buySite = buySite;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getLockedVirtualInv() {
		return lockedVirtualInv;
	}

	public void setLockedVirtualInv(Long lockedVirtualInv) {
		this.lockedVirtualInv = lockedVirtualInv;
	}
	
	
}
