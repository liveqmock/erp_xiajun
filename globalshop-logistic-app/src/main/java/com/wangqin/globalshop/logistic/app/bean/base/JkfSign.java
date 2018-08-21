package com.wangqin.globalshop.logistic.app.bean.base;

import lombok.Builder;
import lombok.Data;

/**
 * 签名信息
 *
 * @author angus
 * @date 2018/8/20
 */
@Data
@Builder
public class JkfSign {

    /**
     * 发送方备案编号
     * 必填：Y
     */
    private String companyCode;

    /**
     * 业务编号
     * 必填：Y
     */
    private String businessNo;

    /**
     * 业务类型
     * 必填：Y
     */
    private String businessType;

    /**
     * 申报类型
     * 必填：Y
     */
    private String declareType;

    /**
     * 必填：Y
     */
    private String cebFlag;

    /**
     * 备注
     * 必填：N
     */
    private String note;
}
