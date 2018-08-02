package com.wangqin.globalshop.biz1.app.enums;

/**
 * 通用状态枚举
 * @author 朱路
 *
 */
public enum AgentStatus {
    /**
     *
     */
    NORMAL(1, "正常"),
    /**
     *
     */
    HAS_LIFT(0,"已解除");
    private Integer code;
    private String description;
    AgentStatus(int code, String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static AgentStatus of(int code) {
        for (AgentStatus status : AgentStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
	
}
