package com.wangqin.globalshop.pay.dto;

import lombok.Data;

import java.util.List;

/**
 * 分账响应参数对应实体类 <br>
 * 响应参数示例：
 * <pre>
 * {
 *     "merchantOrderNo": "b8e8dfc3de4b44c8bcaeadf5e5795eba",
 *     "returnCode": "01",
 *     "sharingReqItem": [
 *         {
 *             "payeeId": "107537",
 *             "payeeIdType": "1",
 *             "sdSharingNo": "20171222132609610259",
 *             "sharingAmount": "0.50",
 *             "sharingNo": "1",
 *             "status": "0"
 *         },
 *         {
 *             "payeeId": "107537",
 *             "payeeIdType": "1",
 *             "sdSharingNo": "20171222132609610260",
 *             "sharingAmount": "0.50",
 *             "sharingNo": "2",
 *             "status": "0"
 *         }
 *     ],
 *     "sharingReqNo": "10d8823a6a814d03b523a23f1c49c5c7",
 *     "status": "P"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/8/15
 */
@Data
public class SharingPayResponse {
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
    private String merchantOrderNo;

    /**
     * 分账请求号 <br>
     * 可空：N
     */
    private String sharingReqNo;

    /**
     * 分账状态 (见下列枚举) <br>
     * C:创建 P:处理中 S:成功 F:失败 R:被风控 <br>
     * 可空：N
     */
    private String status;

    /**
     * 分账信息 <br>
     * 可空：N
     */
    private List<SharingReqItem> sharingReqItem;
}
