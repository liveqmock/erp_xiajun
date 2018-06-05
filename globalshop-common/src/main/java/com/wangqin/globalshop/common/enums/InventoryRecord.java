package com.wangqin.globalshop.common.enums;


import java.util.Date;

/**
 * 库存变动记录
 * 
 * @author liuhui
 *
 */
public  class InventoryRecord {


	/**
	 * 库存类型
	 * 
	 * @author liuhui
	 *
	 */
	public static class InventoryType {
		/**
		 * 在途
		 */
		public static  String TRANS_INV =  "TRANS_INV";
		/**
		 * 实际库存
		 */
		public static  String INVENTORY = " INVENTORY";
	}

	/**
	 * 库存操作类型
	 * 
	 * @author liuhui
	 *
	 */
	public static class OperatorType {
		/**
		 * 预定
		 */
		public static  String LOCKED = "LOCKED";
		/**
		 * 发货
		 */
		public static  String SEND = "SEND";
		/**
		 * 进货
		 */
		public static  String STOCK = "STOCK";

	}

/*	DROP TABLE IF EXISTS`inventory_record`;
	CREATE TABLE`inventory_record`(
	  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	  `order_id` bigint(19) DEFAULT NULL COMMENT '订单id',
	  `order_detail_id` bigint(19) NOT NULL COMMENT '订单详情id',
	  `item_id` bigint(19) DEFAULT NULL COMMENT '商品id',
	  `sku_id` bigint(19) NOT NULL COMMENT 'skuid',
	  `sku_code` varchar(64) DEFAULT NULL COMMENT 'sku码',
	  `quantity` bigint(11) DEFAULT NULL COMMENT '订单购买量',
	  `booked` bigint(11) DEFAULT NULL COMMENT '预定量',
	  `inventory` bigint(11) DEFAULT NULL COMMENT '实际库存',
	  `inventory_type` varchar(64) DEFAULT NULL COMMENT '库存类型',
	  `operator_type` varchar(64) DEFAULT NULL COMMENT '库存操作类型', 
	  `inventory_area_id` bigint(19) DEFAULT NULL COMMENT '仓库库存id',
	  `warehouse_id` bigint(19) DEFAULT NULL COMMENT '仓库ID',
	  `position_no` varchar(64) DEFAULT NULL COMMENT '货架编号',
	  `gmt_create` datetime NOT NULL COMMENT '创建时间',
	  `gmt_modify` datetime NOT NULL COMMENT '操作时间',

	PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='库存预定记录';

*/

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 订单id
	 */
	private String orderNo;
	/**
	 * 商品id
	 */
	private String itemCode;
	/**
	 * skuCode
	 */
	private String skuCode;
	/**
	 * 购买总数量
	 */
	private int quantity;

	/**
	 * 已库存预定量
	 */
	private Long booked;
	/**
	 * 预定时的剩余总库存数
	 * 
	 * type = VIRTUAL 虚拟库存量 ， type = INVENTORY 虚拟库存量
	 */
	private Long inventory;
	/**
	 * 库存类型 : 虚拟/锁定／ 实际
	 */
	private String inventoryType;
	/**
	 * 库存操作类型 ： 预定／发货／采购入库
	 */
	private String  operatorType;

	private Long inventoryAreaId;
	/**
	 * 仓库Id
	 */
	private String warehouseNo;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 货架编号
	 */
	private String positionNo;
	// /**
	// * 采购订单号
	// */
	// @TableField(value = "purchase_order_id")
	// private Long purchaseOrderId;
	// /**
	// * 采购条目
	// */
	// @TableField(value = "purchase_order_id")
	// private Long purchaseOrderDetailId;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModify;

	//
//	@TableField(value = "is_deleted")
//	private boolean isDeleted;

	// 入库人

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}




	public String getWarehouseName() {
		return warehouseName;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getPositionNo() {
		return positionNo;
	}

	public void setPositionNo(String positionNo) {
		this.positionNo = positionNo;
	}

	// public Long getPurchaseOrderId() {
	// return purchaseOrderId;
	// }
	//
	// public void setPurchaseOrderId(Long purchaseOrderId) {
	// this.purchaseOrderId = purchaseOrderId;
	// }
	//
	// public Long getPurchaseOrderDetailId() {
	// return purchaseOrderDetailId;
	// }
	//
	// public void setPurchaseOrderDetailId(Long purchaseOrderDetailId) {
	// this.purchaseOrderDetailId = purchaseOrderDetailId;
	// }

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

//	public boolean isDeleted() {
//		return isDeleted;
//	}
//
//	public void setDeleted(boolean isDeleted) {
//		this.isDeleted = isDeleted;
//	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getBooked() {
		return booked;
	}

	public void setBooked(Long booked) {
		this.booked = booked;
	}

	public Long getInventory() {
		return inventory;
	}

	public void setInventory(Long inventory) {
		this.inventory = inventory;
	}

	public Long getInventoryAreaId() {
		return inventoryAreaId;
	}

	public void setInventoryAreaId(Long inventoryAreaId) {
		this.inventoryAreaId = inventoryAreaId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
}
