package com.wangqin.globalshop.pay.constant;

/**
 * 盛付通“分账状态”对应的枚举
 *
 * @author angus
 * @date 2018/8/14
 */
public enum ShareStatusEnum {

    /**
     * 创建
     */
    CREATE("C", "创建"),

    /**
     * 处理中
     */
    PROCESS("P", "处理中"),

    /**
     * 成功
     */
    SUCCESS("S", "成功"),

    /**
     * 失败
     */
    FAIL("F", "失败"),

    /**
     * 被风控
     */
    RISK("R", "被风控");

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String msg;

    ShareStatusEnum(String code, String msg) {
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
