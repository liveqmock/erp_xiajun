package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 商城的顾客（包括微信小程序和第三方销售平台）
 *
 */
@TableName("mall_customer")
public class MallCustomer implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**  */
	@TableField(value = "customer_no")
	private String customerNo;

	/** 微信open_id */
	@TableField(value = "open_id")
	private String openId;

	/** 微信union_id */
	@TableField(value = "union_id")
	private String unionId;

	/**  */
	@TableField(value = "nick_name")
	private String nickName;

	/** 0未知,1男,2女 */
	private Integer gender;

	/**  */
	private String city;

	/**  */
	private String province;

	/**  */
	private String country;

	/** 头像图片地址 */
	@TableField(value = "portrait_url")
	private String portraitUrl;

	/** 用户来源,跟mall_user_track表关联 */
	private Long refer;

	/** 用户角色 */
	private Integer role;

	/**  */
	@TableField(value = "first_login_time")
	private Date firstLoginTime;

	/**  */
	@TableField(value = "last_login_time")
	private Date lastLoginTime;

	/**  */
	@TableField(value = "first_login_device")
	private Long firstLoginDevice;

	/**  */
	@TableField(value = "last_login_device")
	private Long lastLoginDevice;

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

	/**  */
	@TableField(value = "company_no")
	private String companyNo;

	/**  */
	@TableField(value = "channel_no")
	private String channelNo;

	/**  */
	@TableField(value = "shop_code")
	private String shopCode;


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

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return this.unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPortraitUrl() {
		return this.portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}

	public Long getRefer() {
		return this.refer;
	}

	public void setRefer(Long refer) {
		this.refer = refer;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Date getFirstLoginTime() {
		return this.firstLoginTime;
	}

	public void setFirstLoginTime(Date firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Long getFirstLoginDevice() {
		return this.firstLoginDevice;
	}

	public void setFirstLoginDevice(Long firstLoginDevice) {
		this.firstLoginDevice = firstLoginDevice;
	}

	public Long getLastLoginDevice() {
		return this.lastLoginDevice;
	}

	public void setLastLoginDevice(Long lastLoginDevice) {
		this.lastLoginDevice = lastLoginDevice;
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

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

}
