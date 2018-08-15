package com.wangqin.globalshop.pay.service;

import net.sf.json.JSONString;

/**
 * 个人支付相关 service
 *
 * @author angus
 * @date 2018/8/13
 */
public interface PayService {

    /**
     * 创建支付订单
     *
     * @param merchantOrderNo 商户系统内的唯一订单号，商户订单号不能重复
     * @param amount          该笔订单的交易金额，单位默认为RMB-元，精确到小数点后两位，如：23.42
     * @param productName     商品名称(不超过32个字符)
     * @param userIp          用户IP（用户下单时的IP-公网IP,银联交易必填）
     * @param payChannel      支付渠道
     * @param exts            扩展属性,JSON串（微信H5 、微信小程序、微信APP必传，请参考备注）
     */
    void orderPay(String merchantOrderNo, String amount, String productName,
                  String userIp, String payChannel, String exts);

    /**
     * 单笔查询
     *
     * @param merchantOrderNo 商户系统内的唯一订单号，商户订单号不能重复
     * @param sftOrderNo      盛付通订单号
     * @param exts            扩展属性,JSON串
     */
    void queryPay(String merchantOrderNo, String sftOrderNo, String exts);

    /**
     * 退款
     *
     * @param refundOrderNo   退款请求流水号(商户系统唯一)
     * @param merchantOrderNo 原支付订单号
     * @param refundAmount    退款金额(与支付金额一致)
     * @param exts            扩展属性,JSON串
     */
    void refundPay(String refundOrderNo, String merchantOrderNo, String refundAmount, String exts);

    /**
     * 退款查询
     *
     * @param refundOrderNo   退款请求流水号(商户系统唯一)
     * @param merchantOrderNo 原支付订单号
     * @param refundTransNo   盛付通退款订单号
     * @param sftOrderNo      盛付通系统内针对此商户订单的唯一订单号，如: C20160105105839885474
     * @param exts            扩展属性,JSON串
     */
    void queryRefund(String refundOrderNo, String merchantOrderNo, String refundTransNo,
                     String sftOrderNo, String exts);
}
