package com.wangqin.globalshop.pay.constant;

/**
 * 盛付通“返回码”对应的枚举
 *
 * @author angus
 * @date 2018/8/14
 */
public enum ReturnCodeEnum {

    /**
     * 下单成功
     */
    SUCCESS("00", "下单成功"),

    /**
     * 分账查询成功
     */
    SHARE_SUCCESS("01", "分账查询成功"),

    /**
     * 超时
     */
    TIMEOUT("TIMEOUT", "超时"),

    /**
     * OK
     */
    OK("OK", "OK"),

    /**
     * FAIL
     */
    FAIL("FAIL", "FAIL");

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String msg;

    ReturnCodeEnum(String code, String msg) {
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
