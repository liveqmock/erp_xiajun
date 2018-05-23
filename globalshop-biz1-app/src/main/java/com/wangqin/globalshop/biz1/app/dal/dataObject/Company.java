package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *
 * 买手公司
 *
 */
public class Company implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 索引唯一 */
	@TableField(value = "company_no")
	private String companyNo;

	/** 公司名称 */
	@TableField(value = "company_name")
	private String companyName;

	/** 状态 0:正常，1:关闭 */
	private Integer status;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 修改时间 */
	@TableField(value = "gmt_modified")
	private Date gmtModified;

	/** 公司的店铺名称 */
	@TableField(value = "shop_name")
	private String shopName;

	/**  */
	@TableField(value = "logo_url")
	private String logoUrl;

	/** 介绍 */
	private String intro;

	/** 身份证号，默认需要 */
	@TableField(value = "force_idcard")
	private Integer forceIdcard;

	/**  */
	private String tel;

	/** 及时通讯工具，如微信 */
	private String im;

	/** 服务时间 */
	@TableField(value = "service_time")
	private String serviceTime;

	/** 身份证图片，默认不需要 */
	@TableField(value = "force_idcard_upload")
	private Integer forceIdcardUpload;

	/**  */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/**  */
	private String creator;

	/**  */
	private String modifier;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;


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

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return this.gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getForceIdcard() {
		return this.forceIdcard;
	}

	public void setForceIdcard(Integer forceIdcard) {
		this.forceIdcard = forceIdcard;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIm() {
		return this.im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Integer getForceIdcardUpload() {
		return this.forceIdcardUpload;
	}

	public void setForceIdcardUpload(Integer forceIdcardUpload) {
		this.forceIdcardUpload = forceIdcardUpload;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
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

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
