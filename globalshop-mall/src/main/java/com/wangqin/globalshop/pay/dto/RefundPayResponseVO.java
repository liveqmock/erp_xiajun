package com.wangqin.globalshop.pay.dto;

import lombok.Data;
import net.sf.json.JSONString;

/**
 * 退款响应参数对应的 VO <br>
 * 响应参数示例：
 * <pre>
 * {
 *     "merchantOrderNo": "f2e03275ee25488f99e58630dfb02552",
 *     "orderCreateTime": "20171222111333",
 *     "refundAmount": "3.00",
 *     "refundOrderNo": "4c3f4341ee094044bcf77825b5ab4d59",
 *     "refundTransNo": "20171222111610212",
 *     "returnCode": "01",
 *     "status": "01"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/13
 */
@Data
public class RefundPayResponseVO {
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
     * 退款状态 <br>
     * 可空：N
     */
    private String status;

    /**
     * 退款金额 <br>
     * 可空：N
     */
    private String refundAmount;

    /**
     * 盛付通退款订单号 <br>
     * 可空：N
     */
    private String refundTransNo;

    /**
     * 订单创建时间 <br>
     * 可空：Y
     */
    private String orderCreateTime;

    /**
     * 扩展参数 <br>
     * 可空：Y
     */
    private JSONString exts;
}
