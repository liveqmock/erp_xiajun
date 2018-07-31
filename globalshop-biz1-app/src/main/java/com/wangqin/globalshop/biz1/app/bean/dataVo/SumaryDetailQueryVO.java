package com.wangqin.globalshop.biz1.app.bean.dataVo;

/**
 * Create by 777 on 2018/7/31
 */
public class SumaryDetailQueryVO extends PageQueryVO{

	private String companyNo;

	private String shareUserId;

	private Integer status;

	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getShareUserId() {
		return shareUserId;
	}
	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}
}
