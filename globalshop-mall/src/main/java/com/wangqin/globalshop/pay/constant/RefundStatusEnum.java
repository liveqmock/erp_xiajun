package com.wangqin.globalshop.pay.constant;

/**
 * 盛付通“退款状态”对应的枚举
 *
 * @author angus
 * @date 2018/8/13
 */
public enum RefundStatusEnum {

    /**
     * 退款中
     */
    PROCESS("00", "退款中"),

    /**
     * 退款成功
     */
    SUCCESS("01", "退款成功"),

    /**
     * 退款失败
     */
    FAIL("02", "退款失败");


    /**
     * 状态编码
     */
    private String code;

    /**
     * 状态描述
     */
    private String msg;

    RefundStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RefundStatusEnum of(String code) {
        for (RefundStatusEnum refundStatusEnum : RefundStatusEnum.values()) {
            if (refundStatusEnum.code.equals(code)) {
                return refundStatusEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
