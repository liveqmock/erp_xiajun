package com.wangqin.globalshop.biz1.app.constants.enums;

/**
 * 通用状态枚举
 * @author 朱路
 *
 */
public enum GeneralStatus {

	INIT(0, "新建"),
	CONFIRM(1,"已确认"),
	SUCCESS(2,"成功"),
	CLOSE(-1, "关闭"),
	CONFIRMING(3,"入库中");
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
