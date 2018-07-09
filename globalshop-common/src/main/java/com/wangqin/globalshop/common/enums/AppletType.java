package com.wangqin.globalshop.common.enums;

import java.util.Objects;

public enum AppletType {

	PURCHASE_APPLET("1", "采购小程序"),
	MALL_APPLET("2", "销售小程序");
	
	
	private String value;
	private String desc;
	
	private AppletType(String value, String desc) {
		this.setValue(value);
		this.setDesc(desc);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static AppletType statusOfValue(Integer value) {
		for (AppletType appletType : AppletType.values()) {
			if (Objects.equals(appletType.value, value)) {
				return appletType;
			}
		}
		return null;
	}
}
