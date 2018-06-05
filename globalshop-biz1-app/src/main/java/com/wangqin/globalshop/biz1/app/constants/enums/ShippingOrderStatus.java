package com.wangqin.globalshop.biz1.app.constants.enums;

/**
 * 发货单状态枚举
 * @author 刘洋
 *
 */
public enum ShippingOrderStatus {
	INIT(0, "已预报"),
	SENT(1, "快递已发货"),
	RECEIVE(2, "客户已收货");
	
	private int code;
    private String description;
    
    ShippingOrderStatus(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static ShippingOrderStatus of(int code) {
        for (ShippingOrderStatus status : ShippingOrderStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
