package com.wangqin.globalshop.biz1.app.enums;

/**
 * 采购商品审核状态
 * @author wangkan
 *
 */
public enum PurchaseItemStatus {

	INIT(0, "审核中"),
	PASS(1,"审批通过"),
	DELETE(-1, "已拒绝");
    private int code;
    private String description;
    PurchaseItemStatus(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static PurchaseItemStatus of(int code) {
        for (PurchaseItemStatus status : PurchaseItemStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
	
}
