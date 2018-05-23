package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 买手采购入库表
 *
 */
@TableName("buyer_storage")
public class BuyerStorage implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**  */
	@TableField(value = "storage_no")
	private String storageNo;

	/** 仓库ID */
	@TableField(value = "warehouse_no")
	private String warehouseNo;

	/** 仓库名称 */
	@TableField(value = "warehouse_name")
	private String warehouseName;

	/** 买手名称 */
	@TableField(value = "buyer_name")
	private String buyerName;

	/** 买手ID */
	@TableField(value = "buyer_open_id")
	private Long buyerOpenId;

	/** 入库单号 */
	@TableField(value = "purchase_storage_no")
	private String purchaseStorageNo;

	/** 采购任务编号 */
	@TableField(value = "buyer_task_no")
	private String buyerTaskNo;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 入库方式：0计划采购入库，1扫描入库 */
	@TableField(value = "storage_type")
	private Integer storageType;

	/** 入库时间 */
	@TableField(value = "entry_date")
	private Date entryDate;

	/** 操作时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/** 备注 */
	private String memo;

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

	public String getBuyerName() {
		return this.buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public Long getBuyerOpenId() {
		return this.buyerOpenId;
	}

	public void setBuyerOpenId(Long buyerOpenId) {
		this.buyerOpenId = buyerOpenId;
	}

	public String getPurchaseStorageNo() {
		return this.purchaseStorageNo;
	}

	public void setPurchaseStorageNo(String purchaseStorageNo) {
		this.purchaseStorageNo = purchaseStorageNo;
	}

	public String getBuyerTaskNo() {
		return this.buyerTaskNo;
	}

	public void setBuyerTaskNo(String buyerTaskNo) {
		this.buyerTaskNo = buyerTaskNo;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Integer getStorageType() {
		return this.storageType;
	}

	public void setStorageType(Integer storageType) {
		this.storageType = storageType;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
