package com.wangqin.globalshop.biz1.app.constants.enums;

public enum CompanyStatus {
	Normal(0, "正常"),
	Close(1, "关闭");
	
	private Integer value;
	private String desc;
	
	private CompanyStatus(Integer value, String desc) {
		this.setValue(value);
		this.setDesc(desc);
	}
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
