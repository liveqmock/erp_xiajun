package com.wangqin.globalshop.biz1.app.dal.dataVo;

public class BuyerTaskVO {
	private Long buyerId;
	
	private String itemName;
	
	private String buySite;

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBuySite() {
		return buySite;
	}

	public void setBuySite(String buySite) {
		this.buySite = buySite;
	}
}
