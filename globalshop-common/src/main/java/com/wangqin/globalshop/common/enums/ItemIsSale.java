package com.wangqin.globalshop.common.enums;

/**
 * 商品是否可售
 * (item.is_sale)
 * @author xiajun
 * 默认为1
 */
public enum ItemIsSale {

	UNSALABLE(0, "不可售"),
	SALABLE(1, "可售");
	   
	private Integer code;
	private String message;
	
	ItemIsSale(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	 
}
