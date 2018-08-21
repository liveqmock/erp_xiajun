package com.wangqin.globalshop.logistic.app.bean.cancel;

import lombok.Builder;
import lombok.Data;

/**
 * 删单信息回执
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
public class ModifyCancelResult {
    /**
     * 业务编号
     * 必填：Y
     */
    private String businessNo;

    /**
     * 电商平台备案编号
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
     * 处理时间
     * 必填：Y <br>
     * 格式：yyyyMMddHHmmss
     */
    private String processTime;
}
