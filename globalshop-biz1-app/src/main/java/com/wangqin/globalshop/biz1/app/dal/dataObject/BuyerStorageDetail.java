package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 买手采购入库详情表
 *
 */
@TableName("buyer_storage_detail")
public class BuyerStorageDetail implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 入库单ID */
	@TableField(value = "storage_no")
	private String storageNo;

	/** 仓库ID */
	@TableField(value = "warehouse_no")
	private String warehouseNo;

	/** 仓库名称 */
	@TableField(value = "warehouse_name")
	private String warehouseName;

	/** 货架号 */
	@TableField(value = "shelf_no")
	private String shelfNo;

	/** sku编码 */
	@TableField(value = "sku_code")
	private String skuCode;

	/** 销售价 */
	private Double price;

	/** 总价 */
	@TableField(value = "total_price")
	private Double totalPrice;

	/** 币种 */
	private Integer currency;

	/**  */
	private String upc;

	/** 入库数量 */
	private Integer quantity;

	/** 在途库存 */
	@TableField(value = "trans_quantity")
	private Integer transQuantity;

	/** 操作时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/** 采购入库单号 */
	@TableField(value = "purchase_storage_no")
	private String purchaseStorageNo;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 采购子任务号 */
	@TableField(value = "buyer_task_detail_no")
	private String buyerTaskDetailNo;

	/** 入库单号 */
	@TableField(value = "item_code")
	private Long itemCode;

	/** 订购站点 */
	@TableField(value = "sku_buysite")
	private String skuBuysite;

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

	public String getStorageNo() {
		return this.storageNo;
	}

	public void setStorageNo(String storageNo) {
		this.storageNo = storageNo;
	}

	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getShelfNo() {
		return this.shelfNo;
	}

	public void setShelfNo(String shelfNo) {
		this.shelfNo = shelfNo;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getCurrency() {
		return this.currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getUpc() {
		return this.upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTransQuantity() {
		return this.transQuantity;
	}

	public void setTransQuantity(Integer transQuantity) {
		this.transQuantity = transQuantity;
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

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getBuyerTaskDetailNo() {
		return this.buyerTaskDetailNo;
	}

	public void setBuyerTaskDetailNo(String buyerTaskDetailNo) {
		this.buyerTaskDetailNo = buyerTaskDetailNo;
	}

	public Long getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(Long itemCode) {
		this.itemCode = itemCode;
	}

	public String getSkuBuysite() {
		return this.skuBuysite;
	}

	public void setSkuBuysite(String skuBuysite) {
		this.skuBuysite = skuBuysite;
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
