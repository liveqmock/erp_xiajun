package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName("shipping_track_polling_yuntong")
public class ShippingTrackPollingYuntong implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 运单号 */
	@TableField(value = "waybill_no")
	private String waybillNo;

	/** 国内快递运单号 */
	@TableField(value = "inland_trans_code")
	private String inlandTransCode;

	/** 国内快递公司 */
	@TableField(value = "inland_trans_company_name")
	private String inlandTransCompanyName;

	/** 状态 */
	@TableField(value = "current_status")
	private String currentStatus;

	/** 状态时间 */
	@TableField(value = "status_time")
	private Date statusTime;

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

	public String getWaybillNo() {
		return this.waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getInlandTransCode() {
		return this.inlandTransCode;
	}

	public void setInlandTransCode(String inlandTransCode) {
		this.inlandTransCode = inlandTransCode;
	}

	public String getInlandTransCompanyName() {
		return this.inlandTransCompanyName;
	}

	public void setInlandTransCompanyName(String inlandTransCompanyName) {
		this.inlandTransCompanyName = inlandTransCompanyName;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Date getStatusTime() {
		return this.statusTime;
	}

	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
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
