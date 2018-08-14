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
                  String userIp, String payChannel, JSONString exts);

    /**
     * 单笔查询
     *
     * @param merchantOrderNo 商户系统内的唯一订单号，商户订单号不能重复
     * @param sftOrderNo      盛付通订单号
     */
    void queryPay(String merchantOrderNo, String sftOrderNo);

}
