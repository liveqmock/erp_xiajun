package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.common.utils.AppUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 发货单管理查询表单对应的 VO
 * <p>
 * 前端查询参数示例：
 * <pre>
 *  status: 0
 *  logisticCompany: 申通
 *  logisticNo: EZ486544467CN
 *  erpNo: SUB1533105970733 // 建议更名为 mallOrders
 *  receiver: Angus Liu
 *  telephone: 18328635851
 *  shippingNo: PKG18080209282010003
 *  type: 1
 *  startOrderTime: 2018-08-16
 *  endOrderTime: 2018-08-26
 * </pre>
 *
 * @author angus
 * @date 2018/8/2
 */
@Data
public class ShippingOrderQueryVO {
    /**
     * 物流状态
     * <p>
     * 对应 shipping_order 表的 status 字段
     */
    private Integer status;

    /**
     * 物流公司名称
     * <p>
     * 对应 shipping_order 表的 logistic_company 字段
     */
    private String logisticCompany;

    /**
     * 物流订单号
     * <p>
     * 对应 shipping_order 表的 logistic_no 字段
     */
    private String logisticNo;

    /**
     * 子订单号
     * <p>
     * 对应 shipping_order 表的 mall_orders 字段
     */
    private String mallOrders;

    /**
     * 收件人
     * <p>
     * 对应 shipping_order 表的 receiver 字段
     */
    private String receiver;

    /**
     * 电话
     * <p>
     * 对应 shipping_order 表的 telephone 字段
     */
    private String telephone;

    /**
     * 发货单号
     * <p>
     * 对应 shipping_order 表的 shipping_no 字段
     */
    private String shippingNo;

    /**
     * 发货时间（起）
     * <p>
     * 对应 shipping_order 表的 gmt_create 字段
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startOrderTime;

    /**
     * 发货时间（止）
     * <p>
     * 对应 shipping_order 表的 gmt_create 字段
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endOrderTime;

    /**
     * 渠道
     * <p>
     * 对应 shipping_order 表的 type 字段
     */
    private Integer type;

    /**
     * 公司编号
     */
    private String CompanyNo;

    public ShippingOrderQueryVO() {
        this.CompanyNo = AppUtil.getLoginUserCompanyNo();
    }
}
