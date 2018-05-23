package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 仓库库存
 *
 */
@TableName("inventory_on_warehouse")
public class InventoryOnWarehouse implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**  */
	@TableField(value = "company_no")
	private String companyNo;

	/** 商品id */
	@TableField(value = "item_code")
	private Long itemCode;

	/** 商品名称 */
	@TableField(value = "item_name")
	private String itemName;

	/** 条码 */
	private String upc;

	/** 规格 */
	private String scale;

	/** sku码 */
	@TableField(value = "sku_code")
	private String skuCode;

	/** sku图片信息 */
	@TableField(value = "sku_pic")
	private String skuPic;

	/** 现货库存 */
	private Long inventory;

	/** 现货占用库存 */
	@TableField(value = "locked_inv")
	private Long lockedInv;

	/** 在途库存 */
	@TableField(value = "trans_inv")
	private Long transInv;

	/** 在途占用库存 */
	@TableField(value = "locked_trans_inv")
	private Long lockedTransInv;

	/** 仓库ID */
	@TableField(value = "warehouse_no")
	private Long warehouseNo;

	/** 仓库名称 */
	@TableField(value = "warehouse_name")
	private String warehouseName;

	/** 货架编号 */
	@TableField(value = "shelf_no")
	private String shelfNo;

	/** 批次号 */
	@TableField(value = "batch_no")
	private String batchNo;

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

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public Long getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(Long itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUpc() {
		return this.upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getScale() {
		return this.scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSkuPic() {
		return this.skuPic;
	}

	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public Long getInventory() {
		return this.inventory;
	}

	public void setInventory(Long inventory) {
		this.inventory = inventory;
	}

	public Long getLockedInv() {
		return this.lockedInv;
	}

	public void setLockedInv(Long lockedInv) {
		this.lockedInv = lockedInv;
	}

	public Long getTransInv() {
		return this.transInv;
	}

	public void setTransInv(Long transInv) {
		this.transInv = transInv;
	}

	public Long getLockedTransInv() {
		return this.lockedTransInv;
	}

	public void setLockedTransInv(Long lockedTransInv) {
		this.lockedTransInv = lockedTransInv;
	}

	public Long getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(Long warehouseNo) {
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

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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
