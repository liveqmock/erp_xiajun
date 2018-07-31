package com.wangqin.globalshop.common.enums;

/**
 * 渠道价格类型
 * @author xiajun
 *
 */
public enum ChannelSaleType {
	SAME(0,"全渠道"),
	DIFFERENT(1,"分渠道");


	
	private Integer value;
	private String name;
	
	private ChannelSaleType(int value, String name) {
		this.setValue(value);
		this.setName(name);
	}
	
	public static ChannelSaleType getChannelSaleType(int value) {
		for (ChannelSaleType item : ChannelSaleType.values()) {
			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
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
