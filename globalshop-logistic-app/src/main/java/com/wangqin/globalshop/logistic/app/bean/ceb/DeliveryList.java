package com.wangqin.globalshop.logistic.app.bean.ceb;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 入库明细单表体
 *
 * @author angus
 * @date 2018/8/23
 */
@Data
@Builder
@XStreamAlias("DeliveryList")
public class DeliveryList {

    /**
     * 序号
     * 必填：Y <br>
     * 从1开始的递增序号
     */
    private String gnum;

    /**
     * 物流运单编号
     * 必填：Y <br>
     * 物流企业的运单包裹面单号。同一物流企业的运单编号在6个月内不重复。运单编号长度不能超过60位。
     */
    private String logisticsNo;

    /**
     * 备注
     * 必填：N
     */
    private String note;
}
