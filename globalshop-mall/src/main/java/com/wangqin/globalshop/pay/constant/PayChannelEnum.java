package com.wangqin.globalshop.pay.constant;

/**
 * 盛付通“支付渠道”对应的枚举
 *
 * @author angus
 * @date 2018/8/14
 */
public enum PayChannelEnum {

    /**
     * 微信扫码
     */
    WP("wp", "微信扫码"),

    /**
     * 支付宝扫码
     */
    AP("ap", "支付宝扫码"),

    /**
     * 微信公众号
     */
    OW("ow", "微信公众号"),

    /**
     * 支付宝服务窗
     */
    OA("oa", "支付宝服务窗");

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String msg;

    PayChannelEnum(String code, String msg) {
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
