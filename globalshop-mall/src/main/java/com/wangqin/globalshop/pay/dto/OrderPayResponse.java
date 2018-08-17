package com.wangqin.globalshop.pay.dto;

import lombok.Data;

/**
 * 创建支付订单响应参数对应实体类 <br>
 * 响应参数示例：
 * <pre>
 * {
 *     "exts": "",
 *     "merchantOrderNo": "f2e03275ee25488f99e58630dfb02552",
 *     "orderCreateTime": "2017-12-22 11:11:21",
 *     "payUrl": "weixin://wxpay/bizpayurl?pr=ildFXeJ",
 *     "returnCode": "00",
 *     "sftOrderNo": "C20171222111120651783"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/13
 */
@Data
public class OrderPayResponse {
    // 基本响应参数
    /**
     * 返回码 <br>
     * 可空：N
     */
    private String returnCode;

    /**
     * 详细返回码，详细请参见 <br>
     * 可空：Y
     */
    private String returnDetailCode;

    /**
     * 返回消息 <br>
     * 可空：Y
     */
    private String returnMessage;

    /**
     * MD5 <br>
     * 可空：N
     */
    private String signType;

    /**
     * MD5签名消息 <br>
     * 可空：N
     */
    private String signMsg;

    // 业务参数
    /**
     * 商户系统内的唯一订单号 <br>
     * 可空：N
     */
    private String merchantOrderNo;

    /**
     * 盛付通系统内针对此商户订单的唯一订单号 <br>
     * 如: C20160105105839885474 <br>
     * 可空：N
     */
    private String sftOrderNo;

    /**
     * 微信，支付宝 <br>
     * 可空：Y
     */
    private String payUrl;

    /**
     * 公众号支付信息 <br>
     * 可空：Y
     */
    private PayInfo payInfo;

    /**
     * 扩展信息参数 <br>
     * 可空：Y
     */
    private Exts exts;

    /**
     * 订单创建时间，格式：yyyyMMddHHmmss <br>
     * 如：20160105105839 <br>
     * 可空：N
     */
    private String orderCreateTime;
}
