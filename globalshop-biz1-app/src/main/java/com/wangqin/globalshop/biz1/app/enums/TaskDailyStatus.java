package com.wangqin.globalshop.biz1.app.enums;

public enum TaskDailyStatus {
	INIT(0, "待采购"),
	CONFIRM(1, "已完成"),
	CLOSE(-1, "已取消"),
	PURCHASING(2,"采购中");
	
	private int code;
    private String description;
    
    TaskDailyStatus(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static TaskDailyStatus of(int code) {
        for (TaskDailyStatus status : TaskDailyStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
