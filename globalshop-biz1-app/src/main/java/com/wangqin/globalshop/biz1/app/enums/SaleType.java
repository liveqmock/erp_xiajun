package com.wangqin.globalshop.biz1.app.enums;

/**
 * 销售类型枚举
 * @author 刘洋
 *
 */
public enum SaleType {
	TOBUY(0, "代购"),
	STORE(1, "现货");
	
	private int code;
    private String description;
    
    SaleType(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static SaleType of(int code) {
        for (SaleType status : SaleType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
