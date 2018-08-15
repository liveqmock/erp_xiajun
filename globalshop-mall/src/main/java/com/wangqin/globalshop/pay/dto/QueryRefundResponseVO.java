package com.wangqin.globalshop.pay.dto;

import lombok.Data;
import net.sf.json.JSONString;

/**
 * 退款查询响应参数对应的 VO <br>
 * 响应参数示例：
 * <pre>
 * {
 *     "orderAmount": "3.00",
 *     "refundAmount": "3.00",
 *     "refundOrderNo": "4c3f4341ee094044bcf77825b5ab4d59",
 *     "refundTime": "20171222111335",
 *     "returnCode": "01",
 *     "status": "01"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/13
 */
@Data
public class QueryRefundResponseVO {
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
     * 退款请求流水号(商户系统唯一) <br>
     * 可空：N
     */
    private String refundOrderNo;

    /**
     * 原支付订单号 <br>
     * 可空：N
     */
    private String merchantOrderNo;

    /**
     * 盛付通退款订单号 <br>
     * 可空：Y
     */
    private String refundTransNo;

    /**
     * 盛付通系统内针对此商户订单的唯一订单号 <br>
     * 如: C20160105105839885474 <br>
     * 可空：Y
     */
    private String sftOrderNo;

    /**
     * 退款状态 <br>
     * 可空：N
     */
    private String status;

    /**
     * 订单金额 <br>
     * 可空：N
     */
    private String orderAmount;

    /**
     * 退款金额 <br>
     * 可空：N
     */
    private String refundAmount;

    /**
     * 退款时间 <br>
     * 可空：N
     */
    private String refundTime;

    /**
     * 扩展参数 <br>
     * 可空：Y
     */
    private JSONString exts;
}
