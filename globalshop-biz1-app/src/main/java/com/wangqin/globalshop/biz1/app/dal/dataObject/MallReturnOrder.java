package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 顾客在商城的退货单
 *
 */
@TableName("mall_return_order")
public class MallReturnOrder implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 主订单号 */
	@TableField(value = "order_no")
	private String orderNo;

	/**  */
	@TableField(value = "outer_order_no")
	private Long outerOrderNo;

	/** 子订单号 */
	@TableField(value = "sub_order_no")
	private String subOrderNo;

	/** 退单状态 */
	private Integer status;

	/** 退货原因分类 */
	@TableField(value = "return_reason")
	private String returnReason;

	/** 退货原因明细 */
	@TableField(value = "return_reason_detail")
	private String returnReasonDetail;

	/** 退货数量 */
	@TableField(value = "return_quantity")
	private Integer returnQuantity;

	/** 退款金额 */
	@TableField(value = "return_price")
	private Double returnPrice;

	/** 是否国内退货 */
	@TableField(value = "is_civil")
	private Integer isCivil;

	/** 是否入库 */
	@TableField(value = "is_checkin")
	private Integer isCheckin;

	/** 小程序消费者微信open_id */
	@TableField(value = "customer_open_id")
	private String customerOpenId;

	/** 联系电话 */
	private String telephone;

	/** 收货时间 */
	@TableField(value = "receive_time")
	private Date receiveTime;

	/** 退款时间 */
	@TableField(value = "return_pay_time")
	private Date returnPayTime;

	/** 备注 */
	private String desc;

	/** 退款来源（0:erp创建；1：小程序） */
	@TableField(value = "return_refer")
	private Integer returnRefer;

	/** 退款上传图片凭证 */
	@TableField(value = "proof_img")
	private String proofImg;

	/** 退款形式（0直接退款;1既退货又退款） */
	@TableField(value = "return_type")
	private Integer returnType;

	/** 创建时间(售后发起时间) */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;

	/**  */
	private String creator;

	/**  */
	private String modifier;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getOuterOrderNo() {
		return this.outerOrderNo;
	}

	public void setOuterOrderNo(Long outerOrderNo) {
		this.outerOrderNo = outerOrderNo;
	}

	public String getSubOrderNo() {
		return this.subOrderNo;
	}

	public void setSubOrderNo(String subOrderNo) {
		this.subOrderNo = subOrderNo;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReturnReason() {
		return this.returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getReturnReasonDetail() {
		return this.returnReasonDetail;
	}

	public void setReturnReasonDetail(String returnReasonDetail) {
		this.returnReasonDetail = returnReasonDetail;
	}

	public Integer getReturnQuantity() {
		return this.returnQuantity;
	}

	public void setReturnQuantity(Integer returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public Double getReturnPrice() {
		return this.returnPrice;
	}

	public void setReturnPrice(Double returnPrice) {
		this.returnPrice = returnPrice;
	}

	public Integer getIsCivil() {
		return this.isCivil;
	}

	public void setIsCivil(Integer isCivil) {
		this.isCivil = isCivil;
	}

	public Integer getIsCheckin() {
		return this.isCheckin;
	}

	public void setIsCheckin(Integer isCheckin) {
		this.isCheckin = isCheckin;
	}

	public String getCustomerOpenId() {
		return this.customerOpenId;
	}

	public void setCustomerOpenId(String customerOpenId) {
		this.customerOpenId = customerOpenId;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getReceiveTime() {
		return this.receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getReturnPayTime() {
		return this.returnPayTime;
	}

	public void setReturnPayTime(Date returnPayTime) {
		this.returnPayTime = returnPayTime;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getReturnRefer() {
		return this.returnRefer;
	}

	public void setReturnRefer(Integer returnRefer) {
		this.returnRefer = returnRefer;
	}

	public String getProofImg() {
		return this.proofImg;
	}

	public void setProofImg(String proofImg) {
		this.proofImg = proofImg;
	}

	public Integer getReturnType() {
		return this.returnType;
	}

	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
