package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *
 * 总的库存记录
 *
 */
public class Inventory implements Serializable {

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
	private String itemCode;

	/** 商品名称 */
	@TableField(value = "item_name")
	private String itemName;

	/** 条码 */
	private String upc;

	/** sku码 */
	@TableField(value = "sku_code")
	private String skuCode;

	/** 虚拟库存 */
	@TableField(value = "virtual_inv")
	private Long virtualInv;

	/** 虚拟占用库存(仅用来同步第三方平台可售库存时使用) */
	@TableField(value = "locked_virtual_inv")
	private Long lockedVirtualInv;

	/** 现货库存 */
	private Long inv;

	/** 现货占用库存 */
	@TableField(value = "locked_inv")
	private Long lockedInv;

	/** 在途库存 */
	@TableField(value = "trans_inv")
	private Long transInv;

	/** 在途占用库存 */
	@TableField(value = "locked_trans_inv")
	private Long lockedTransInv;

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

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
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

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Long getVirtualInv() {
		return this.virtualInv;
	}

	public void setVirtualInv(Long virtualInv) {
		this.virtualInv = virtualInv;
	}

	public Long getLockedVirtualInv() {
		return this.lockedVirtualInv;
	}

	public void setLockedVirtualInv(Long lockedVirtualInv) {
		this.lockedVirtualInv = lockedVirtualInv;
	}

	public Long getInv() {
		return this.inv;
	}

	public void setInv(Long inv) {
		this.inv = inv;
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
