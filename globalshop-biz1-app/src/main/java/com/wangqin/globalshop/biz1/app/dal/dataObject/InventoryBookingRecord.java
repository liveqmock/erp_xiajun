package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 库存备货记录表
 *
 */
@TableName("inventory_booking_record")
public class InventoryBookingRecord implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**  */
	@TableField(value = "company_no")
	private String companyNo;

	/** 订单id */
	@TableField(value = "order_no")
	private String orderNo;

	/** 子订单ID */
	@TableField(value = "sub_order_no")
	private String subOrderNo;

	/** 商品id */
	@TableField(value = "item_code")
	private String itemCode;

	/** sku码 */
	@TableField(value = "sku_code")
	private String skuCode;

	/** 订单购买量 */
	private Long quantity;

	/** 预定量 */
	@TableField(value = "booked_quantity")
	private Long bookedQuantity;

	/** 实际库存 */
	private Long inventory;

	/** 库存类型 */
	@TableField(value = "inventory_type")
	private String inventoryType;

	/** 库存操作类型 */
	@TableField(value = "operator_type")
	private String operatorType;

	/** 仓库库存id */
	@TableField(value = "inventory_area_id")
	private Long inventoryAreaId;

	/** 仓库ID */
	@TableField(value = "warehouse_id")
	private Long warehouseId;

	/** 货架编号 */
	@TableField(value = "position_no")
	private String positionNo;

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

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSubOrderNo() {
		return this.subOrderNo;
	}

	public void setSubOrderNo(String subOrderNo) {
		this.subOrderNo = subOrderNo;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getBookedQuantity() {
		return this.bookedQuantity;
	}

	public void setBookedQuantity(Long bookedQuantity) {
		this.bookedQuantity = bookedQuantity;
	}

	public Long getInventory() {
		return this.inventory;
	}

	public void setInventory(Long inventory) {
		this.inventory = inventory;
	}

	public String getInventoryType() {
		return this.inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getOperatorType() {
		return this.operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public Long getInventoryAreaId() {
		return this.inventoryAreaId;
	}

	public void setInventoryAreaId(Long inventoryAreaId) {
		this.inventoryAreaId = inventoryAreaId;
	}

	public Long getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getPositionNo() {
		return this.positionNo;
	}

	public void setPositionNo(String positionNo) {
		this.positionNo = positionNo;
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
