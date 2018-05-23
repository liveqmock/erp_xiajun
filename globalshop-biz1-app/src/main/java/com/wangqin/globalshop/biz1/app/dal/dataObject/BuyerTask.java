package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 采购任务，一个任务可以含有多种商品
 *
 */
@TableName("buyer_task")
public class BuyerTask implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**  */
	@TableField(value = "buyer_task_no")
	private String buyerTaskNo;

	/**  */
	private String title;

	/**  */
	@TableField(value = "owner_no")
	private String ownerNo;

	/**  */
	private String desc;

	/**  */
	@TableField(value = "start_time")
	private Date startTime;

	/**  */
	@TableField(value = "end_time")
	private Date endTime;

	/** -1为已取消，0为待采购，2为采购中，1为采购结束 */
	private Integer status;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/**  */
	@TableField(value = "buyer_open_id")
	private Long buyerOpenId;

	/** 买手 */
	@TableField(value = "buyer_name")
	private String buyerName;

	/** 采购佣金模式 */
	@TableField(value = "purchase_commission_mode")
	private Long purchaseCommissionMode;

	/**  */
	@TableField(value = "purchase_commission_str")
	private String purchaseCommissionStr;

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

	public String getBuyerTaskNo() {
		return this.buyerTaskNo;
	}

	public void setBuyerTaskNo(String buyerTaskNo) {
		this.buyerTaskNo = buyerTaskNo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public Long getBuyerOpenId() {
		return this.buyerOpenId;
	}

	public void setBuyerOpenId(Long buyerOpenId) {
		this.buyerOpenId = buyerOpenId;
	}

	public String getBuyerName() {
		return this.buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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
