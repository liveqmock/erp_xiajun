package com.wangqin.globalshop.biz1.app.dal.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDetailDO;

/**
 * Create by 777 on 2018/7/10
 */
public class BuyerReceiptDetailVo extends BuyerReceiptDetailDO {

	private String itemName;

	private String skuPic;

	private Double costPrice;

	private Double disCount;


	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSkuPic() {
		return skuPic;
	}
	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getDisCount() {
		return disCount;
	}
	public void setDisCount(Double disCount) {
		this.disCount = disCount;
	}
}
