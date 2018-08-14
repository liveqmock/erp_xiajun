package com.wangqin.globalshop.pay.constant;

/**
 * 盛付通“支付状态”对应的枚举
 *
 * @author angus
 * @date 2018/8/13
 */
public enum PayStatusEnum {

    /**
     * 等待付款中
     */
    WAIT("00", "等待付款中"),

    /**
     * 提交成功
     */
    SUCCESS("01", "提交成功"),

    /**
     * 提交失败
     */
    FAIL("02", "提交失败"),

    /**
     * 过期
     */
    EXPIRED("03", "过期"),

    /**
     * 已退款
     */
    REFUND("06", "已退款"),

    /**
     * 已支付风险订单
     */
    RISK("09", "已支付风险订单"),

    /**
     * 风控审核中
     */
    AUDIT("10", "风控审核中"),

    /**
     * 风险订单支付拒绝,退款中
     */
    REFUSE("11", "风险订单支付拒绝,退款中");

    /**
     * 状态编码
     */
    private String code;

    /**
     * 状态描述
     */
    private String msg;

    PayStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static PayStatusEnum of(String code) {
        for (PayStatusEnum payStatusEnum : PayStatusEnum.values()) {
            if (payStatusEnum.code.equals(code)) {
                return payStatusEnum;
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
