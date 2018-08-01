package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

/**
 * Create by 777 on 2018/7/31
 */
public class SettlementQueryVO extends PageQueryVO {


	private String companyNo;//公司

	private String key;  //结算单号、代理人名字

	private int status;//1待结算，2可结算，3已结算

	private Date settlementTime; //结算时间

	private String shareUserId;


	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getShareUserId() {
		return shareUserId;
	}
	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getSettlementTime() {
		return settlementTime;
	}
	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}
}
