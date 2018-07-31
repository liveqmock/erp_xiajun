package com.wangqin.globalshop.biz1.app.enums;

/**
 * 国家枚举
 * @author 刘洋
 *
 */
public enum CountryType {
	ITALY(1,"意大利"),
	FRANCE(2,"法国"),
	SPAIN(3,"西班牙"),
	USA(4, "美国"),
	GERMANY(5, "德国"),
	JAPAN(6, "日本"),
	AUSTRALIA(7, "澳洲");
	
	private int code;
    private String description;
    
    CountryType(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static CountryType of(int code) {
        for (CountryType status : CountryType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
