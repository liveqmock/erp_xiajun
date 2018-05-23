package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 顾客的订单（在微信小程序或者第三方销售平台）
 *
 */
@TableName("mall_order")
public class MallOrder implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 消费者的编号 */
	@TableField(value = "customer_no")
	private String customerNo;

	/** 订单编号 */
	@TableField(value = "order_no")
	private String orderNo;

	/** 公司编号 */
	@TableField(value = "company_no")
	private String companyNo;

	/** 渠道编号 */
	@TableField(value = "channel_no")
	private String channelNo;

	/** 销售名称 */
	@TableField(value = "channel_name")
	private String channelName;

	/** 第三方平台订单编号 */
	@TableField(value = "channel_order_no")
	private String channelOrderNo;

	/**  */
	@TableField(value = "channel_customer_no")
	private String channelCustomerNo;

	/**  */
	@TableField(value = "channel_type")
	private String channelType;

	/** 公司店铺的编号 */
	@TableField(value = "shop_code")
	private String shopCode;

	/** 微信支付商户号 */
	@TableField(value = "wx_pay_trade_no")
	private String wxPayTradeNo;

	/** 应付金额 */
	@TableField(value = "total_amount")
	private Double totalAmount;

	/** 实付金额 */
	@TableField(value = "actual_amount")
	private Double actualAmount;

	/** 支付方式 */
	@TableField(value = "pay_type")
	private Integer payType;

	/** 下单时间 */
	@TableField(value = "order_time")
	private Date orderTime;

	/** 订单状态 */
	private Integer status;

	/** 备注 */
	private String memo;

	/** 身份证号号码 */
	@TableField(value = "id_card")
	private String idCard;

	/** 身份证正面照 */
	@TableField(value = "idcard_pic_front")
	private String idcardPicFront;

	/** 身份证反面照 */
	@TableField(value = "idcard_pic_reverse")
	private String idcardPicReverse;

	/** 用户来源,跟wx_app_launch表关联 */
	private Long source;

	/** 实际邮费，包含在实付金额里面 */
	private Double freight;

	/** 实际邮费，代理 */
	@TableField(value = "freight_agent")
	private Double freightAgent;

	/** 操作时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;

	/**  */
	private String modifier;

	/**  */
	private String creator;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getChannelNo() {
		return this.channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelOrderNo() {
		return this.channelOrderNo;
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}

	public String getChannelCustomerNo() {
		return this.channelCustomerNo;
	}

	public void setChannelCustomerNo(String channelCustomerNo) {
		this.channelCustomerNo = channelCustomerNo;
	}

	public String getChannelType() {
		return this.channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getWxPayTradeNo() {
		return this.wxPayTradeNo;
	}

	public void setWxPayTradeNo(String wxPayTradeNo) {
		this.wxPayTradeNo = wxPayTradeNo;
	}

	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getActualAmount() {
		return this.actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdcardPicFront() {
		return this.idcardPicFront;
	}

	public void setIdcardPicFront(String idcardPicFront) {
		this.idcardPicFront = idcardPicFront;
	}

	public String getIdcardPicReverse() {
		return this.idcardPicReverse;
	}

	public void setIdcardPicReverse(String idcardPicReverse) {
		this.idcardPicReverse = idcardPicReverse;
	}

	public Long getSource() {
		return this.source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public Double getFreight() {
		return this.freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getFreightAgent() {
		return this.freightAgent;
	}

	public void setFreightAgent(Double freightAgent) {
		this.freightAgent = freightAgent;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
