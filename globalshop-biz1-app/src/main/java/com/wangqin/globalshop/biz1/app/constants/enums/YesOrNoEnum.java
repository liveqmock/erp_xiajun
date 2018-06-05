package com.wangqin.globalshop.biz1.app.constants.enums;

public enum YesOrNoEnum {

	YES(1, "是"),
	NO(0, "否");
	
	private int code;
    private String description;
    YesOrNoEnum(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static YesOrNoEnum of(int code) {
        for (YesOrNoEnum status : YesOrNoEnum.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
