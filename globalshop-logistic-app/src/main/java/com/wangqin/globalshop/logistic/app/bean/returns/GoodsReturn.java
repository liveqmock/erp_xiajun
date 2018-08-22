package com.wangqin.globalshop.logistic.app.bean.returns;

import lombok.Builder;
import lombok.Data;

/**
 * 退单表头对象
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
public class GoodsReturn {

    /**
     * 退货申报编号
     * 必填：Y <br>
     * 退货申报编号(原运单号+4位数序列)
     */
    private String appCode;

    /**
     * 原订单编号
     * 必填：Y <br>
     * 原订单编号
     */
    private String orderNo;

    /**
     * 原运单号
     * 必填：Y
     */
    private String wayBillNo;

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
     * 仓储企业代码
     * 必填：N <br>
     * 企业在跨境电商通关服务平台的备(保税进口时必填)
     */
    private String internalAreaCompanyNo;

    /**
     * 申报企业编码
     * 必填：Y
     */
    private String declareCompanyCode;

    /**
     * 退货运单号
     * 必填：N <br>
     * 指新的运单编号，未放行的非必填
     */
    private String returnWayBillNo;

    /**
     * 申报时间
     * 必填：Y <br>
     * 格式：2014-07-05 18:01:01
     */
    private String declareTimeStr;

    /**
     * 场地代码
     * 必填：Y <br>
     * 见参数表
     */
    private String customsField;

    /**
     * 申报关区
     * 必填：Y <br>
     * 见参数表
     */
    private String declPort;

    /**
     * 件数
     * 必填：Y
     */
    private Double packNo;

    /**
     * 退货原因
     * 必填：Y
     */
    private String reason;
}
