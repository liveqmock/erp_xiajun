package com.wangqin.globalshop.pay.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 单笔查询请求参数对应实体类 <br>
 * 生产地址：http://mgw.shengpay.com/web-acquire-channel/pay/query.htm <br>
 * 请求类型：REST <br>
 * 请求方式：POST <br>
 * 请求参数示例：
 * <pre>
 * {
 *     "requestTime": "20171222111300",
 *     "charset": "UTF-8",
 *     "merchantOrderNo": "f2e03275ee25488f99e58630dfb02552",
 *     "merchantNo": "540511"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/13
 */
@Data
@Builder
public class QueryPayRequest {
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
     * 盛付通订单号 <br>
     * 可空：Y
     */
    private String sftOrderNo;


    /**
     * 扩展属性,JSON串 <br>
     * 可空：Y
     */
    private String exts;
}