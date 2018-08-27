package com.wangqin.globalshop.biz1.app.enums;

/**
 * 渠道类型
 * @author zhubowen
 *
 */
public enum ChannelType {
	YouZan(1,"有赞"),
	HaiHu(2,"海狐海淘"),
	TaoBao(3,"淘宝"),

	JingDong(4,"京东"),

	IntraMirror(5,"IntraMirror"),

	WEIXIN(101, "微信"),
	WEIXINXCX(102, "微信小程序");

	private int value;
	private String name;
	
	private ChannelType(int value, String name) {
		this.setValue(value);
		this.setName(name);
	}
	
	public static ChannelType getChannelType(int value) {
		for (ChannelType item : ChannelType.values()) {
			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public static String getChannelName(Integer value){
		String tmpName = "";
		ChannelType channelType = getChannelType(value);
		if(channelType != null){
			tmpName = channelType.getName();
		}
		return tmpName;
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
