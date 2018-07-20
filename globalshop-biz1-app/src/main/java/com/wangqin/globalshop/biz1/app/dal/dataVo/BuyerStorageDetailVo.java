package com.wangqin.globalshop.biz1.app.dal.dataVo;

import java.util.Date;

/**
 * 打平buyer_storage+buyer_storage_detail,供前台展示
 *
 * Create by 777 on 2018/6/21
 */
public class BuyerStorageDetailVo {

	private Long id;//detaliId

	private String storageNo;//入库单号

	private String skuName; //商品名，来源item_sku

	private String skuCode; //商品名，来源item_sku

	private String upc;

	private String specifications;//规格

	private Integer quantity; //小程序端，这个是线下数量，

	private Integer transQuantity; //在途，预入库数，

	private Integer entryQuantity; //实际入库数=客户手工填写入库数

	private Integer preQuantity; //预入库数=在途+线下

	private String buyerName; //买手名字

	private String buyerOpenId; //买手微信ID

	protected Date gmtCreate; //创建时间

	protected Date gmtModify; //最后更新时间

	private String warehouseNo; //仓库NO

	private String warehouseName; //仓库名字

	private String shelfNo;

	private String itemCode;

	private String statusName;

	private Integer status;

	private String opUserNo;

	private String opUserName;

	private String companyNo;

	private Date opTime;

	private Integer batchNum;

	private String buyerTaskNo;

	private String mem;

	public String getMem() {
		return mem;
	}
	public void setMem(String mem) {
		this.mem = mem;
	}
	public Integer getPreQuantity() {
		return preQuantity;
	}
	public void setPreQuantity(Integer preQuantity) {
		this.preQuantity = preQuantity;
	}
	public Integer getEntryQuantity() {
		return entryQuantity;
	}
	public void setEntryQuantity(Integer entryQuantity) {
		this.entryQuantity = entryQuantity;
	}
	public String getBuyerTaskNo() {
		return buyerTaskNo;
	}
	public void setBuyerTaskNo(String buyerTaskNo) {
		this.buyerTaskNo = buyerTaskNo;
	}
	public Integer getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(Integer batchNum) {
		this.batchNum = batchNum;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getOpUserName() {
		return opUserName;
	}
	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
	public String getOpUserNo() {
		return opUserNo;
	}
	public void setOpUserNo(String opUserNo) {
		this.opUserNo = opUserNo;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStorageNo() {
		return storageNo;
	}
	public void setStorageNo(String storageNo) {
		this.storageNo = storageNo;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTransQuantity() {
		return transQuantity;
	}
	public void setTransQuantity(Integer transQuantity) {
		this.transQuantity = transQuantity;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerOpenId() {
		return buyerOpenId;
	}
	public void setBuyerOpenId(String buyerOpenId) {
		this.buyerOpenId = buyerOpenId;
	}
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
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getShelfNo() {
		return shelfNo;
	}
	public void setShelfNo(String shelfNo) {
		this.shelfNo = shelfNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
}
