package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

/**
 * Create by 777 on 2018/7/31
 */
public class SettlementQueryVO extends PageQueryVO {


	private String companyNo;//公司

	private String key;  //结算单号、代理人名字

	private Integer status; //

	private Date settlementTime; //结算时间

	private String shareUserId;

	private Boolean isDel;

	public Boolean getDel() {
		return isDel;
	}
	public void setDel(Boolean del) {
		isDel = del;
	}
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getSettlementTime() {
		return settlementTime;
	}
	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}
}
