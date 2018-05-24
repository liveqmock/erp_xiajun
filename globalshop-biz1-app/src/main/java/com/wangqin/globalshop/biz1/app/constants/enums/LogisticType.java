package com.wangqin.globalshop.biz1.app.constants.enums;

public enum LogisticType {
	DIRECT(0, "直邮"),
	GROUP(1, "拼邮");
	
    private int code;
    private String description;
    
    LogisticType(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static LogisticType of(int code) {
        for (LogisticType status : LogisticType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
