package com.wangqin.globalshop.pay.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 分账查询响应参数子项对应实体类
 *
 * @author angus
 * @date 2018/8/15
 */
@Data
@Builder
public class SharingResultItem {
    /**
     * 分帐子项请求序号 <br>
     * 可空：N
     */
    private String sharingNo;

    /**
     * 盛付通子分账流水号 <br>
     * 可空：N
     */
    private String sdSharingNo;

    /**
     * 盛付通分账流水号 <br>
     * 可空：N
     */
    private String sharingReqNo;

    /**
     * 分账金额 <br>
     * 可空：N
     */
    private String sharingAmount;

    /**
     * 会员标识 <br>
     * 可空：N
     */
    private String payeeId;

    /**
     * 子分账状态
     * <li> 0:处理中 </li>
     * <li> 1:成功 </li>
     * 可空：N
     */
    private String status;

    /**
     * 会员类型（枚举见下列附录）
     * <li> 1：商户号 </li>
     * <li> 4：memberid </li>
     * 可空：N
     */
    private String payeeIdType;
}
