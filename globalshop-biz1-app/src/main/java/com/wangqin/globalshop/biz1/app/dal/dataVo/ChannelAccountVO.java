package com.wangqin.globalshop.biz1.app.dal.dataVo;

public class ChannelAccountVO extends PageQueryVO {
	private Integer type;
	private String name;
	private Long companyId;
	private Integer status;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
