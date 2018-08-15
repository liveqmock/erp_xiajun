package com.wangqin.globalshop.pay.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 分账信息请求/响应参数子项对应实体类
 *
 * @author angus
 * @date 2018/8/15
 */
@Data
@Builder
public class SharingReqItem {

    // 请求与响应共有参数
    /**
     * 分帐子项请求序号 <br>
     * 可空：N
     */
    private String sharingNo;

    /**
     * 分帐金额，如10.00表示10元 <br>
     * 可空：N（sharingAmount 与 sharingRate 二选一）
     */
    private String sharingAmount;

    /**
     * 分帐比例 如0.50表示50% <br>
     * 可空：N（sharingAmount 与 sharingRate 二选一）
     */
    private String sharingRate;

    /**
     * 会员标识 <br>
     * 可空：N
     */
    private String payeeId;

    /**
     * 会员类型 1：商户号，4：memberid <br>
     * 可空：N
     */
    private String payeeIdType;

    // 响应附加参数
    /**
     * 分账状态：0处理中，1成功 <br>
     * 可空：N
     */
    private String status;
}
