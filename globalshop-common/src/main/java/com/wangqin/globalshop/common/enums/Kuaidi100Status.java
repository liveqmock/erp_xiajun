package com.wangqin.globalshop.common.enums;

import java.util.Objects;

public enum Kuaidi100Status {
	NotNeed_Subscribe(0, "不需订阅"),
	Need_Subscribe(1, "需要订阅，但未订阅"),
	Subscribed(2, "已订阅");
	
	
	private Integer value;
	private String desc;
	
	private Kuaidi100Status(Integer value, String desc) {
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
	
	public static Kuaidi100Status statusOfValue(Integer value) {
		for (Kuaidi100Status item : Kuaidi100Status.values()) {
			if (Objects.equals(item.value, value)) {
				return item;
			}
		}
		return null;
	}
}
