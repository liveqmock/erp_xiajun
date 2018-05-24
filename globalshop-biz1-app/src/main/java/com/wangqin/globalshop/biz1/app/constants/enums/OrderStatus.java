package com.wangqin.globalshop.biz1.app.constants.enums;

/**
 * 订单状态枚举
 * @author 朱路
 *
 */
public enum OrderStatus {

	INIT(0, "待付款"), 
	PAID(3, "已付款待发货"), 
	PART_SENT(1, "部分发货"), 
	SENT(2, "全部发货"), 
	CLOSE(-1, "关闭"), 
	MONEY_WITHDRAW(-2, "退款中"), 
	RETURNING(-3, "退货中"),
	RETURNDONE(-4, "退货完成"), 
	SUCCESS(4, "订单完成");
	
    private int code;
    private String description;
    OrderStatus(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static OrderStatus of(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
	
}
