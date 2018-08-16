package com.wangqin.globalshop.biz1.app.enums;

import java.util.ArrayList;
import java.util.List;

public enum OrderReturnStatus {
	INIT(0, "售后待审核"),
	RECEIVE(1, "审核通过"),
	PAY(2, "退款完成"),
    RETURN_COMPLETED(3, "退货完成"),
	CLOSE(-1, "售后完成");
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
    
    //首页数据看板：未完成售后的订单
    public static List<Integer> returningOrderStatus() {
    	List<Integer> statusList = new ArrayList<Integer>();
    	statusList.add(INIT.code);
    	statusList.add(RECEIVE.code);    	
    	statusList.add(PAY.code);
    	statusList.add(RETURN_COMPLETED.code);
    	return statusList;
    }
}
