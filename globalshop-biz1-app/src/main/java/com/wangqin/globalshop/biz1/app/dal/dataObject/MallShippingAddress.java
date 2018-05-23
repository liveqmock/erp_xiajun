package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 顾客收件地址
 *
 */
@TableName("mall_shipping_address")
public class MallShippingAddress implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**  */
	@TableField(value = "channel_no")
	private String channelNo;

	/**  */
	@TableField(value = "customer_no")
	private String customerNo;

	/**  */
	@TableField(value = "open_id")
	private String openId;

	/**  */
	private String receiver;

	/**  */
	@TableField(value = "receiver_state")
	private String receiverState;

	/**  */
	@TableField(value = "receiver_city")
	private String receiverCity;

	/**  */
	@TableField(value = "receiver_district")
	private String receiverDistrict;

	/**  */
	@TableField(value = "address_detail")
	private String addressDetail;

	/**  */
	private String telephone;

	/**  */
	@TableField(value = "identity_card")
	private String identityCard;

	/**  */
	@TableField(value = "idcard_pic_front")
	private String idcardPicFront;

	/**  */
	@TableField(value = "idcard_pic_reverse")
	private String idcardPicReverse;

	/**  */
	@TableField(value = "is_default")
	private Integer isDefault;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;

	/**  */
	private String creator;

	/**  */
	private String modifier;


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannelNo() {
		return this.channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverState() {
		return this.receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverCity() {
		return this.receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return this.receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getAddressDetail() {
		return this.addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
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

	public Integer getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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
