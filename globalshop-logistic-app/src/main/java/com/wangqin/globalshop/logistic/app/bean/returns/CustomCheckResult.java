package com.wangqin.globalshop.logistic.app.bean.returns;

import lombok.Builder;
import lombok.Data;

/**
 * 退货审核回执
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
public class CustomCheckResult {

    /**
     * 业务编号
     * 必填：Y
     */
    private String businessNo;

    /**
     * IMPORT_ORDER_RETURN
     */
    private String businessType;

    /**
     * 发送方企业备案编号
     * 必填：Y
     */
    private String companyCode;

    /**
     * 审批结果
     * 必填：Y <br>
     * 21:撤销成功
     * 22:撤销失败
     */
    private String approveResult;

    /**
     * 审批意见
     * 必填：Y <br>
     * 海关审批意见
     */
    private String approveComment;

    /**
     * 审核时间
     * 必填：Y <br>
     * 格式：yyyyMMddHHmmss
     */
    private String processTime;
}
