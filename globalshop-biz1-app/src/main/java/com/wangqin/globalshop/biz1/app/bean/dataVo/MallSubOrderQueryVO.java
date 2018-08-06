package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 发货管理查询表单的对应的 VO
 * <p>
 * 前端查询参数示例：
 * <pre>
 *  orderNo: OR1531203363230
 *  erpNo: SUB1533302265480 // 建议改为 subOrderNo
 *  targetNo: OUT1234567890 // 建议改为 shopCode
 *  status: 0
 *  stockStatus: 0
 *  skuCode: S0101005Q1000390002
 *  itemName: 红牛
 *  upc: upc12334
 *  receiver: Angus Liu
 *  logisticType: 0
 *  telephone: 18328635851
 *  warehouseId: 125 // 建议改为 warehouseNo
 *  closeReason: 退单
 *  openId: o3ngM5IU12dyw0eWc6hz_BGNvIYc
 *  idCard: 1
 *  startGmtCreate: 2018-08-15
 *  endGmtCreate: 2018-08-26
 * </pre>
 *
 * @author angus
 * @date 2018/8/6
 */
@Data
public class MallSubOrderQueryVO {
    /**
     * 主订单号
     * <p>
     * 对应 mall_sub_order 表的 order_no 字段
     */
    private String orderNo;

    /**
     * 子订单号
     * <p>
     * 对应 mall_sub_order 表的 sub_order_no 字段
     */
    private String subOrderNo;

    /**
     * 外部订单号
     * <p>
     * 对应 mall_sub_order 表的 shop_code 字段
     */
    private String shopCode;

    /**
     * 订单状态
     * <p>
     * 对应 mall_sub_order 表的 status 字段
     */
    private Integer status;

    /**
     * 备货状态
     * <p>
     * 对应 mall_sub_order 表的 stock_status 字段
     */
    private Integer stockStatus;

    /**
     * SKU 代码
     * <p>
     * 对应 mall_sub_order 表的 sku_code 字段
     */
    private String skuCode;

    /**
     * 商品名称
     * <p>
     * 对应 mall_sub_order 表的 item_name 字段
     */
    private String itemName;

    /**
     * UPC
     * <p>
     * 对应 mall_sub_order 表的 upc 字段
     */
    private String upc;

    /**
     * 收件人
     * <p>
     * 对应 mall_sub_order 表的 receiver 字段
     */
    private String receiver;

    /**
     * 发货方式
     * <p>
     * 对应 mall_sub_order 表的 logistic_type 字段
     */
    private Integer logisticType;

    /**
     * 手机号码
     * <p>
     * 对应 mall_sub_order 表的 telephone 字段
     */
    private String telephone;

    /**
     * 仓库
     * <p>
     * 对应 mall_sub_order 表的 warehouse_no 字段
     */
    private String warehouseNo;

    /**
     * 关闭理由
     * <p>
     * 对应 mall_sub_order 表的 close_reason 字段
     */
    private String closeReason;

    /**
     * 代理商
     * <p>
     * 对应 mall_sub_order 表的 open_id 字段
     */
    private String openId;

    /**
     * 身份证（有/无）
     * <p>
     * 对应 mall_sub_order 表的 id_card 字段
     */
    private String idCard;

    /**
     * 创建时间（起）
     * <p>
     * 对应 mall_sub_order 表的 gmt_create 字段
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startGmtCreate;

    /**
     * 创建时间（止）
     * <p>
     * 对应 mall_sub_order 表的 gmt_create 字段
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endGmtCreate;
}
