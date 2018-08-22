package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单管理展示列表项对应的 VO
 *
 * @author angus
 * @date 2018/8/3
 */
@Data
public class MallOrderItemVO {

    /**
     * 主订单号
     * <p>
     * 对应 mall_order 表的 order_no 字段
     */
    private String orderNo;

    /**
     * 外部订单号
     * <p>
     * 对应 mall_order 表的 channel_order_no 字段
     */
    private String channelOrderNo;

    /**
     * 商户订单号
     * <p>
     * 对应 mall_order 表的 shop_code 字段
     */
    private String shopCode;

    /**
     * 订单时间
     * <p>
     * 对应 mall_order 表的 order_time 字段
     */
    private Date orderTime;

    /**
     * 订单状态
     * <p>
     * 对应 mall_order 表的 status 字段
     */
    private Integer status;

    /**
     * 收件人
     * <p>
     * 对应 mall_sub_order 表的 receiver 字段
     */
    private String receiver;

    /**
     * 收件地址（国家）
     * <p>
     * 对应 mall_sub_order 表的 receiver_country 字段
     */
    private String receiverCountry;

    /**
     * 收件地址（省）
     * <p>
     * 对应 mall_sub_order 表的 receiver_state 字段
     */
    private String receiverState;

    /**
     * 收件地址（市）
     * <p>
     * 对应 mall_sub_order 表的 receiver_city 字段
     */
    private String receiverCity;

    /**
     * 收件地址（区）
     * <p>
     * 对应 mall_sub_order 表的 receiver_district 字段
     */
    private String receiverDistrict;

    /**
     * 收件地址（详细）
     * <p>
     * 对应 mall_sub_order 表的 receiver_address 字段
     */
    private String addressDetail;

    /**
     * 联系电话
     * <p>
     * 对应 mall_sub_order 表的 telephone 字段
     */
    private String telephone;

    /**
     * 备注
     * <p>
     * 对应 mall_sub_order 表的 memo 字段
     */
    private String remark;

    // 以下是修改列表所需要额外展示的属性

    /**
     * 身份证号
     * <p>
     * 对应 mall_sub_order 表的 id_card 字段
     */
    private String idCard;

    /**
     * 支付方式
     * <p>
     * 对应 mall_order 表的 pay_type 字段
     */
    private Integer payType;

    /**
     * SKU 代码
     * <p>
     * 对应 mall_sub_order 表的 sku_code 字段
     */
    private String skuCode;

	/**
	 * 总价
	 */
	private Double totalAmount;

	/**
	 * 实付价
	 */
	private Double actualAmount;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;


	/**
	 * 修改时间
	 */
	private Date gmtModify;

    /**
	 * 子订单列表
	 */
	private List<MallSubOrderDO> subOrderDOList;
}
