package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

public class ReceiptQueryVO extends PageQueryVO {
	private String receiptNo;
	
	private Date startGmtCreate;

	private Date endGmtCreate;

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
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
}
