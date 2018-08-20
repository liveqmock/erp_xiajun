package com.wangqin.globalshop.pay.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 创建支付订单请求参数对应实体类 <br>
 * 生产地址：http://mgw.shengpay.com/web-acquire-channel/pay/order.htm <br>
 * 请求类型：REST <br>
 * 请求方式：POST <br>
 * 请求参数示例：
 * <pre>
 * {
 *     "requestTime": "20171222111120",
 *     "charset": "UTF-8",
 *     "amount": "3.00",
 *     "expireTime": "20171222131120",
 *     "notifyUrl": "http://45.77.198.110:4799/javademo/notify.jsp",
 *     "userIp": "123.123.123.123",
 *     "currency": "CNY",
 *     "payChannel": "wp",
 *     "merchantOrderNo": "f2e03275ee25488f99e58630dfb02552",
 *     "productName": "测试商品",
 *     "merchantNo": "540511"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/13
 */
@Data
@Builder
public class OrderPayRequest {
    // 基本请求参数
    /**
     * 由盛付通提供,默认为:商户号(由盛付通提供的8位正整数),用于盛付通判别请求方的身份 <br>
     * 如：10143274 <br>
     * 可空：N
     */
    private String merchantNo;

    /**
     * 字符集,支持GBK、UTF-8、GB2312 <br>
     * 默认属性值为:UTF-8 <br>
     * 可空：Y
     */
    private String charset;

    /**
     * 请求时间: yyyyMMddHHmmss <br>
     * 如:20110707112233 <br>
     * 可空：N
     */
    private String requestTime;

    /**
     * 签名类型 <br>
     * 支持的签名方式为: MD5 <br>
     * 可空：N
     */
    private String signType;

    /**
     * 签名消息 <br>
     * 可空：N
     */
    private String signMsg;

    // 业务参数
    /**
     * 商户系统内的唯一订单号，商户订单号不能重复 <br>
     * 可空：N
     */
    private String merchantOrderNo;

    /**
     * 该笔订单的交易金额，单位默认为RMB-元 <br>
     * 精确到小数点后两位，如：23.42 <br>
     * 可空：N
     */
    private String amount;

    /**
     * 商户提交订单申请支付过期的时间,必须为14位正整数数字，且在商户系统保证唯一(yyyyMMddHHmmss)
     * <br>
     * 可空：Y
     */
    private String expireTime;

    /**
     * 盛付通支付平台在用户支付成功后通知商户服务端的地址 <br>
     * 如：http://test.shengpay.com/mas/notify.php <br>
     * 可空：N
     */
    private String notifyUrl;

    /**
     * 商品名称(不超过32个字符) <br>
     * 可空：N
     */
    private String productName;

    /**
     * 货币类型，见附录3币种枚举定义：Currency
     * CNY:人民币 <br>
     * 可空：N
     */
    private String currency;

    /**
     * 用户IP（用户下单时的IP-公网IP,银联交易必填）<br>
     * 可空：N
     */
    private String userIp;

    /**
     * 支付渠道，见附录 <br>
     * 可空：N
     */
    private String payChannel;

    /**
     * 公众号，服务窗支付，银联支付码，微信条码，支付宝条码，微信小程序，微信APP支付必填 <br>
     * 可空：Y
     */
    private String openid;

    /**
     * 同步跳转URL（微信H5，支付宝H5，公众号，服务窗必传,同时这个地址的一级域名必须和报备的授权域名一致）<br>
     * 可空：N
     */
    private String pageUrl;

    /**
     * 扩展属性,JSON串（微信H5 、微信小程序、微信APP必传，请参考备注）<br>
     * 可空：Y
     */
    private Exts exts;
}
