package com.wangqin.globalshop.biz1.app.constants.enums;

public enum OrderReturnStatus {
	INIT(0, "待审核"),
	RECEIVE(1, "审核通过,退款中"),
	PAY(2, "退款成功"),
	CLOSE(-1, "关闭");
    private int code;
    private String description;
    OrderReturnStatus(int code, String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static OrderReturnStatus of(int code) {
        for (OrderReturnStatus status : OrderReturnStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
