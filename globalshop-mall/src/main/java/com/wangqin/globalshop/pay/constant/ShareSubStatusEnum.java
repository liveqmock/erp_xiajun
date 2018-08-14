package com.wangqin.globalshop.pay.constant;

/**
 * 盛付通“分账子项状态”对应的枚举
 *
 * @author wh
 */
public enum ShareSubStatusEnum {

    /**
     * 处理中
     */
    PROCESS("0", "处理中"),

    /**
     * 成功
     */
    SUCCESS("1", "成功");

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String msg;

    ShareSubStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
