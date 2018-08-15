package com.wangqin.globalshop.pay.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 分账请求参数对应实体类 <br>
 * 生产地址：http://mgw.shengpay.com/web-acquire-channel/pay/sharing.htm <br>
 * 请求类型：REST <br>
 * 请求方式：POST <br>
 * 请求参数示例：
 * <pre>
 * {
 *     "requestTime": "20171222132609",
 *     "charset": "UTF-8",
 *     "sharingReqItem": [
 *         {
 *             "sharingNo": "1",
 *             "sharingRate": "0.50",
 *             "payeeId": "107537",
 *             "payeeIdType": "1"
 *         },
 *         {
 *             "sharingNo": "2",
 *             "sharingRate": "0.50",
 *             "payeeId": "107537",
 *             "payeeIdType": "1"
 *         }
 *     ],
 *     "sharingOrderNo": "10d8823a6a814d03b523a23f1c49c5c7",
 *     "merchantOrderNo": "b8e8dfc3de4b44c8bcaeadf5e5795eba",
 *     "merchantNo": "540511"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/15
 */
@Data
@Builder
public class SharingPayRequest {
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
     * 分账请求订单号 <br>
     * 可空：N
     */
    private String sharingOrderNo;

    /**
     * 商户订单号 <br>
     * 可空：N
     */
    private String merchantOrderNo;

    /**
     * 分账异步通知地址 <br>
     * 可空：N
     */
    private String notifyURL;

    /**
     * 分账信息（允许有多个子项请求,集合类型） <br>
     * 可空：N
     */
    private List<SharingReqItem> sharingReqItem;

    /**
     * 扩展属性,JSON串 <br>
     * 可空：Y
     */
    private String exts;
}
