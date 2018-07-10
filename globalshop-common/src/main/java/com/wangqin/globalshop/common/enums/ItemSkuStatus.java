package com.wangqin.globalshop.common.enums;

/**
 * sku的状态
 * (item_sku.status)
 * @author xiajun
 * 0：未审核，1：审核通过
 * 默认未1
 */
public enum ItemSkuStatus {

	UN_CHECK(0, "未审核"),
	CHECKED(1, "审核通过");
	   
	private Integer code;
	private String message;
	
	ItemSkuStatus(Integer code, String message) {
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
