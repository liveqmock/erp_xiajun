package com.wangqin.globalshop.biz1.app.dal.dataVo;

import java.util.Date;
import com.wangqin.globalshop.common.utils.PageQueryVO;

public class TaskReceiptQueryVO extends PageQueryVO {
	private String receiptNo;
	
	private String upc;
	
	private String skuBuysite;
	
	private String itemName;
	
	private Date startGmtCreate;

	private Date endGmtCreate;

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getSkuBuysite() {
		return skuBuysite;
	}

	public void setSkuBuysite(String skuBuysite) {
		this.skuBuysite = skuBuysite;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
