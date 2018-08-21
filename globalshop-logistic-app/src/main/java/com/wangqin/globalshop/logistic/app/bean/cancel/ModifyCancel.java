package com.wangqin.globalshop.logistic.app.bean.cancel;

import lombok.Builder;
import lombok.Data;

/**
 * 删单信息
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
public class ModifyCancel {
    /**
     * 电商企业编码
     * 必填：Y <br>
     * 电商平台下的商家备案编号
     */
    private String eCommerceCode;

    /**
     * 电商平台编码
     * 必填：Y <br>
     * 企业在跨境电商通关服务平台的备案编号
     */
    private String eCompanyCode;

    /**
     * 分运单号
     * 必填：Y
     */
    private String subCarriageNo;

    /**
     * 删单原因
     * 必填：Y
     */
    private String reason;
}
