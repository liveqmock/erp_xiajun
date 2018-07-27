package com.wangqin.globalshop.biz1.app.enums;

/**
 * 站内信阅读状态,对应site_msg_read.status
 * @author XiaJun
 *
 */
public enum SiteMsgReadStatus {
	Read(0,"已读"),
	UnRead(1,"未读");
	
	private int value;
	private String name;
	
	private SiteMsgReadStatus(int value, String name) {
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
