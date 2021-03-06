package com.wangqin.globalshop.biz1.app.enums;

import java.util.ArrayList;
import java.util.List;



/**
 * 订单状态枚举
 * @author 朱路
 *
 */
public enum OrderStatus {
    /**
     * 待付款
     */
    INIT(0, "待付款"),
    /**
     * 新建
     */
    NEW(6, "新建"),
    /**
     * 已付款待发货
     */
    PAID(3, "已付款待发货"),
    /**
     * 部分发货
     */
    PART_SENT(1, "部分发货"),
    /**
     * 全部发货
     */
    SENT(2, "全部发货"),
    /**
     * 关闭
     */
    CLOSE(-1, "关闭"),
    /**
     * 售后处理中
     */
    RETURNING(-3, "售后处理中"),
    /**
     * 售后完成
     */
    RETURNDONE(-4, "售后完成"),
    /**
     * 订单完成
     */
    SUCCESS(4, "订单完成"),
    /**
     * 已签收
     */
    COMFIRM(5, "已签收");




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
    
    //已付款状态的集合
    public static List<Integer> paidList() {
    	List<Integer> paidStatus = new ArrayList<Integer>();
    	paidStatus.add(NEW.code);
    	paidStatus.add(PAID.code);
    	paidStatus.add(PART_SENT.code);
    	paidStatus.add(SENT.code);
    	paidStatus.add(RETURNING.code);
    	paidStatus.add(RETURNDONE.code);
    	paidStatus.add(SUCCESS.code);
    	paidStatus.add(COMFIRM.code);
    	return paidStatus;
    }
    
    //已付款状态(不含确定退款)状态集合
    public static List<Integer> paidListNotReturn() {
    	List<Integer> paidStatus = new ArrayList<Integer>();
    	paidStatus.add(NEW.code);
    	paidStatus.add(PAID.code);
    	paidStatus.add(PART_SENT.code);
    	paidStatus.add(SENT.code);
    	paidStatus.add(SUCCESS.code);
    	paidStatus.add(COMFIRM.code);
    	return paidStatus;
    }
    
    //待发货状态集合
    public static List<Integer> waitSendList() {
    	List<Integer> paidStatus = new ArrayList<Integer>();
    	paidStatus.add(NEW.code);
    	paidStatus.add(PAID.code);
    	paidStatus.add(PART_SENT.code);
    	return paidStatus;
    }
	
}
