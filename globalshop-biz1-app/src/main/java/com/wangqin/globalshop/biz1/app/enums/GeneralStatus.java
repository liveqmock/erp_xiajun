package com.wangqin.globalshop.biz1.app.enums;

/**
 * 通用状态枚举
 * @author 朱路
 *
 */
public enum GeneralStatus {

	INIT(0, "预入库"),//
	CONFIRM(1,"已确认"),//buyer_storage_detail单条明细入库确认
	SUCCESS(2,"成功"),//buyer_storage数量全部入库
	CLOSE(-1, "关闭"),//buyer_storage，和buyer_storage_detail
	CONFIRMING(3,"入库中");//buyer_storage和buyer_storage_detail数量部分确认
    private int code;
    private String description;
    GeneralStatus(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static GeneralStatus of(int code) {
        for (GeneralStatus status : GeneralStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
	
}
