package com.wangqin.globalshop.biz1.app.enums;

/**
 * 商品的上架方式
 * item.shelf_method
 * @author xiajun
 *
 */
public enum ItemShelfMethod {

	SALE_IMMEDIATE(0,"立即售卖"),
	TEMP_UNSALE(1,"暂不售卖"),
	SELF_DEFINE_TIME(2,"自定义上架时间");

	private Integer value;
	private String name;
	
	private ItemShelfMethod(Integer value, String name) {
		this.setValue(value);
		this.setName(name);
	}
	

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
