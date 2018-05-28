package com.wangqin.globalshop.biz1.app.constants.enums;

public enum AccountConfigKey {
		
	LAST_TRADES_GET_TIME(1,"LAST_TRADES_GET_TIME");//上一次订单同步时间	
	
	 private int code;
	 private String description;
	 
	 private AccountConfigKey(int code, String description) {
		this.setCode(code);
		this.setDescription(description);
	 }
	 
	 public static AccountConfigKey getAccountConfigKey(int code) {
			for (AccountConfigKey item : AccountConfigKey.values()) {
				if (item.getCode() == code) {
					return item;
				}
			}
			return null;
		}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	 
	 

}
