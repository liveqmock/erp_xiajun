package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

/**
 * 发货单详情对应的VO
 * <p>
 * 涉及到的表：shipping_order、mall_sub_order
 *
 * @author angus
 * @date 2018/7/27
 */
public class ShippingOrderDetailVO {
    /**
     * 物流方式
     * <br>
     * 对应 shipping_order 表中的 logistic_company 字段
     */
    private String logisticCompany;

    /**
     * 发货时间
     * <br>
     * 对应 shipping_order 表中的 gmt_create 字段
     */
    private Date gmtCreate;

    /**
     * 销售时间
     * <br>
     * 对应 mall_sub_order 表中的 order_time 字段
     */
    private Date orderTime;

    /**
     * 收件人
     * <br>
     * 对应 shipping_order 表中的 receiver 字段，mall_sub_order 与 shipping_order 表中均有该字段
     */
    private String receiver;

    /**
     * 收件地址-省
     * <br>
     * 对应 shipping_order 表中的 receiver_state 字段，mall_sub_order 与 shipping_order 表中均有该字段
     */
    private String receiverState;

    /**
     * 收件地址-市
     * <br>
     * 对应 shipping_order 表中的 receiver_city 字段，mall_sub_order 与 shipping_order 表中均有该字段
     */
    private String receiverCity;

    /**
     * 收件地址-市
     * <br>
     * 对应 shipping_order 表中的 receiver_district 字段，mall_sub_order 与 shipping_order 表中均有该字段
     */
    private String receiverDistrict;

    /**
     * 收件地址-详细
     * <br>
     * 对应 shipping_order 表中的 address 字段，mall_sub_order 与之对应为 receiver_address
     */
    private String address;

    /**
     * 收件人联系方式
     * <br>
     * 对应 shipping_order 表中的 telephone 字段，mall_sub_order 与 shipping_order 表中均有该字段
     */
    private String telephone;

    /**
     *
     */
    private String subOrderNo;


}
