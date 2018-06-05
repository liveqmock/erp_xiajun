package com.wangqin.globalshop.biz1.app.constants.enums;

public enum SexType {

	MAN(1, "男"),
	WOMAN(2, "女"),
	UNKNOWN(3, "未知");
    private int code;
    private String description;
    SexType(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static SexType of(int code) {
        for (SexType status : SexType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
