package com.wangqin.globalshop.biz1.app.enums;

/**
 * Create by 777 on 2018/7/31
 */
public enum SettlementStatus {


	wait(0, "待结算"),
	can(1,"可结算"),//
	SUCCESS(2,"已结算"),
	CLOSE(-1, "关闭");

	private int code;
	private String description;
	SettlementStatus(int code,String description){
		this.code = code;
		this.description = description;
	}
	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static SettlementStatus of(int code) {
		for (SettlementStatus status : SettlementStatus.values()) {
			if (status.code == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("invalid status code " + code);
	}
}
