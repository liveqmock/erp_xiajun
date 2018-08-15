package com.wangqin.globalshop.pay.dto;

import lombok.Data;

import java.util.List;

/**
 * 分账查询响应参数对应实体类 <br>
 * 响应参数示例：
 * <pre>
 * {
 *     "SharingResultItem": [
 *         {
 *             "payeeId": "107537",
 *             "payeeIdType": "1",
 *             "sdSharingNo": "20171225171601720240",
 *             "sharingAmount": "0.50",
 *             "sharingNo": "2",
 *             "sharingReqNo": "20171225171601784997",
 *             "status": "1"
 *         },
 *         {
 *             "payeeId": "107537",
 *             "payeeIdType": "1",
 *             "sdSharingNo": "20171225171601720239",
 *             "sharingAmount": "0.50",
 *             "sharingNo": "1",
 *             "sharingReqNo": "20171225171601784997",
 *             "status": "1"
 *         }
 *     ],
 *     "paymentOrderNo": "f4511ec61ac04dc5b4bc766a39b7f5aa",
 *     "returnCode": "01",
 *     "sharingQueryOrderNo": "09ecfe7e1a074290a50930c5781e28fe"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/15
 */
@Data
public class QuerySharingResponse {
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
     * 商户订单号 <br>
     * 可空：N
     */
    private String paymentOrderNo;

    /**
     * 分账查询请求订单号 <br>
     * 可空：N
     */
    private String sharingQueryOrderNo;

    /**
     * 分账信息 <br>
     * 可空：N
     */
    private List<SharingResultItem> sharingResultItem;

}
