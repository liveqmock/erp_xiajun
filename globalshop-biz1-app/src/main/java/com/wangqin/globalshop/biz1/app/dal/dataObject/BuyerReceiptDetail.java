package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 采购发票详情，发票里面的每一种商品对应一条记录
 *
 */
@TableName("buyer_receipt_detail")
public class BuyerReceiptDetail implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** SKU ID */
	@TableField(value = "sku_code")
	private String skuCode;

	/**  */
	@TableField(value = "item_code")
	private String itemCode;

	/** 采购价 */
	private Double price;

	/** 采购数量 */
	private Integer quantity;

	/**  */
	private String upc;

	/**  */
	@TableField(value = "receipt_no")
	private String receiptNo;

	/**  */
	@TableField(value = "buyer_task_detail_no")
	private String buyerTaskDetailNo;

	/** 在途数量 */
	@TableField(value = "trans_quantity")
	private Integer transQuantity;

	/** 订购站点（方便采购人员标记使用） */
	@TableField(value = "sku_buysite")
	private String skuBuysite;

	/** 预入库单ID */
	@TableField(value = "purchase_storage_no")
	private String purchaseStorageNo;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 操作时间 */
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

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUpc() {
		return this.upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getReceiptNo() {
		return this.receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getBuyerTaskDetailNo() {
		return this.buyerTaskDetailNo;
	}

	public void setBuyerTaskDetailNo(String buyerTaskDetailNo) {
		this.buyerTaskDetailNo = buyerTaskDetailNo;
	}

	public Integer getTransQuantity() {
		return this.transQuantity;
	}

	public void setTransQuantity(Integer transQuantity) {
		this.transQuantity = transQuantity;
	}

	public String getSkuBuysite() {
		return this.skuBuysite;
	}

	public void setSkuBuysite(String skuBuysite) {
		this.skuBuysite = skuBuysite;
	}

	public String getPurchaseStorageNo() {
		return this.purchaseStorageNo;
	}

	public void setPurchaseStorageNo(String purchaseStorageNo) {
		this.purchaseStorageNo = purchaseStorageNo;
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
