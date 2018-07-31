package com.wangqin.globalshop.biz1.app.enums;

/**
 * 站内信状态，对应site_msg.status
 * @author XiaJun
 *
 */
public enum SiteMsgStatus {
	Done(0,"已处理"),
	UnDone(1,"未处理");
	
	private int value;
	private String name;
	
	private SiteMsgStatus(int value, String name) {
		this.setValue(value);
		this.setName(name);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
