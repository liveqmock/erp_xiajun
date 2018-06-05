package com.wangqin.globalshop.biz1.app.constants.enums;

public enum TaskStatusEnum {
                            done(1, "已完成", "done"), cancel(-1, "取消", "cancel"), doing(0, "待采购", "doing"),
                            purchasing(2, "采购中", "purchasing");

    private int    code;
    private String description;
    private String value;

    TaskStatusEnum(int code, String description, String value){
        this.code = code;
        this.description = description;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TaskStatusEnum of(int code) {
        for (TaskStatusEnum status : TaskStatusEnum.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
