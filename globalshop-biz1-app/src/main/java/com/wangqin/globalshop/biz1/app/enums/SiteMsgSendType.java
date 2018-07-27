package com.wangqin.globalshop.biz1.app.enums;

/**
 * 站内信发送方式,对应site_msg.send_type
 * @author XiaJun
 *
 */
public enum SiteMsgSendType {
	Single(0,"单发"),
	Group(1,"群发");
	
	private int value;
	private String name;
	
	private SiteMsgSendType(int value, String name) {
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
