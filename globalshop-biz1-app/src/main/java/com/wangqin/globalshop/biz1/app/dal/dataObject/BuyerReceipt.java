package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 采购的发票，一个发票可以对应好几种的商品
 *
 */
@TableName("buyer_receipt")
public class BuyerReceipt implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** buyer_task表里面的buyer_task_id */
	@TableField(value = "buyer_task_no")
	private String buyerTaskNo;

	/** 发票编号 */
	@TableField(value = "receipt_no")
	private String receiptNo;

	/** 总价 */
	@TableField(value = "total_price")
	private Double totalPrice;

	/** 币种 */
	private String currency;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 操作时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/** 预入库单ID */
	@TableField(value = "purchase_storage_no")
	private String purchaseStorageNo;

	/** 小票状态 */
	private Integer status;

	/** 买手ID */
	@TableField(value = "buyer_id")
	private Long buyerId;

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

	public String getReceiptNo() {
		return this.receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public String getPurchaseStorageNo() {
		return this.purchaseStorageNo;
	}

	public void setPurchaseStorageNo(String purchaseStorageNo) {
		this.purchaseStorageNo = purchaseStorageNo;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getBuyerId() {
		return this.buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
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
