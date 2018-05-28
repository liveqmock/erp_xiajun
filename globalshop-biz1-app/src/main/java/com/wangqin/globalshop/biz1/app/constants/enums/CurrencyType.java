package com.wangqin.globalshop.biz1.app.constants.enums;

/**
 * 币种枚举
 * @author 刘洋
 *
 */
public enum CurrencyType {
	RMB(1, "人民币"),
	DOLLAR(2, "美元"),
    Euro(3,"欧元"),
    HKD(4,"港币");

	private int code;
    private String description;
    
    CurrencyType(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static CurrencyType of(int code) {
        for (CurrencyType status : CurrencyType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
