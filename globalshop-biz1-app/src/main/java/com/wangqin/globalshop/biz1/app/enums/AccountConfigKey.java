package com.wangqin.globalshop.biz1.app.enums;

public enum AccountConfigKey {
		
	LAST_TRADES_GET_TIME(1,"LAST_TRADES_GET_TIME"),//上一次订单同步时间

	LAST_ITEM_GET_TIME(2,"LAST_ITEM_GET_TIME"),//上一次订单同步时间

	NEED_GET_CATEGORY(3,"NEED_GET_CATEGORY"),//是否需要获取过类目

	NEED_GET_ALL_ITEMS(4,"NEED_GET_ALL_ITEMS");//是否需要获取所有商品,true,false
	
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
