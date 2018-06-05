package com.wangqin.globalshop.biz1.app.dal.dataVo;

import java.util.Date;

/**
 * 库存流水查询返回
 * @author zhulu
 *
 */
public class InventoryInoutVO {

	
	private Long id;

	/**
	 * skuCode
	 */
	private String skuCode;
	/**
	 * 出入库数量
	 */
	private int quantity;


	private String itemName;
	
	private String upc;
	
	private String skuPic;
	/**
	 * 仓库Id
	 */
	private String warehouseNo;
	
	private String warehouseName;
	/**
	 * 货架编号
	 */
	private String shelfNo;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Integer operatorType;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}



	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Integer getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getSkuPic() {
		return skuPic;
	}
	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getShelfNo() {
		return shelfNo;
	}
	public void setShelfNo(String shelfNo) {
		this.shelfNo = shelfNo;
	}
	
}
