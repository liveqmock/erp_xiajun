package com.wangqin.globalshop.pay.dto;

import lombok.Data;

/**
 * 单笔查询响应参数对应实体类 <br>
 * 响应参数示例：
 * <pre>
 * {
 *     "exts": "",
 *     "merchantOrderNo": "f2e03275ee25488f99e58630dfb02552",
 *     "orderAmount": "3.00",
 *     "returnCode": "01",
 *     "sftOrderNo": "C20171222111120651783",
 *     "transAmount": "3.00",
 *     "transStatus": "01",
 *     "transTime": "20171222111214"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/13
 */
@Data
public class QueryPayResponse {
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
     * 支付状态 <br>
     * 可空：N
     */
    private String transStatus;

    /**
     * 金额 <br>
     * 可空：N
     */
    private String orderAmount;

    /**
     * 金额 <br>
     * 可空：Y
     */
    private String transAmount;

    /**
     * 时间 <br>
     * 可空：Y
     */
    private String transTime;

    /**
     * 扩展参数 <br>
     * 可空：Y
     */
    private String exts;
}
