package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *
 * 买手
 *
 */
public class Buyer implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 所属公司id */
	@TableField(value = "company_no")
	private String companyNo;

	/** 微信open_id */
	@TableField(value = "open_id")
	private String openId;

	/** 微信union_id */
	@TableField(value = "union_id")
	private String unionId;

	/**  */
	@TableField(value = "nick_name")
	private String nickName;

	/** 头像 */
	@TableField(value = "head_protrait_url")
	private String headProtraitUrl;

	/** 0未知,1男,2女 */
	private Integer gender;

	/**  */
	private String city;

	/**  */
	private String province;

	/**  */
	private String country;

	/** 用户来源,跟mall_user_track表关联 */
	private Long referer;

	/** 采购佣金模式 */
	@TableField(value = "purchase_commission_mode")
	private Long purchaseCommissionMode;

	/** 采购佣金表达式 */
	@TableField(value = "purchase_commission_str")
	private String purchaseCommissionStr;

	/**  */
	@TableField(value = "first_login_time")
	private Date firstLoginTime;

	/**  */
	@TableField(value = "last_login_time")
	private Date lastLoginTime;

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


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
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

	public String getHeadProtraitUrl() {
		return this.headProtraitUrl;
	}

	public void setHeadProtraitUrl(String headProtraitUrl) {
		this.headProtraitUrl = headProtraitUrl;
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

	public Long getReferer() {
		return this.referer;
	}

	public void setReferer(Long referer) {
		this.referer = referer;
	}

	public Long getPurchaseCommissionMode() {
		return this.purchaseCommissionMode;
	}

	public void setPurchaseCommissionMode(Long purchaseCommissionMode) {
		this.purchaseCommissionMode = purchaseCommissionMode;
	}

	public String getPurchaseCommissionStr() {
		return this.purchaseCommissionStr;
	}

	public void setPurchaseCommissionStr(String purchaseCommissionStr) {
		this.purchaseCommissionStr = purchaseCommissionStr;
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
