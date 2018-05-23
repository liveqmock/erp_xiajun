package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 采购任务的详情，一种商品对应一条记录
 *
 */
@TableName("buyer_task_detail")
public class BuyerTaskDetail implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**  */
	@TableField(value = "buyer_task_no")
	private String buyerTaskNo;

	/**  */
	@TableField(value = "item_code")
	private String itemCode;

	/**  */
	private String upc;

	/** 采购价 */
	private Float price;

	/**  */
	@TableField(value = "max_price")
	private Float maxPrice;

	/** 商品采购数量 */
	private Integer count;

	/** 分配人名称 */
	@TableField(value = "owner_name")
	private String ownerName;

	/** 分配人ID */
	@TableField(value = "owner_no")
	private String ownerNo;

	/** 0 美元 1人民币 */
	private Integer currency;

	/** 买手名称 */
	@TableField(value = "buyer_name")
	private String buyerName;

	/** 买手微信ID */
	@TableField(value = "buyer_open_id")
	private Long buyerOpenId;

	/** -1为已取消，0为待采购，2为采购中，1为采购结束 */
	private Integer status;

	/**  */
	@TableField(value = "start_time")
	private Date startTime;

	/**  */
	@TableField(value = "end_time")
	private Date endTime;

	/** 采购方式 0 线上 1线下 */
	private Integer mode;

	/** sku代码 */
	@TableField(value = "sku_code")
	private String skuCode;

	/** 说明 */
	private String desc;

	/** 最大采购数量 */
	@TableField(value = "max_count")
	private Integer maxCount;

	/** sku图片 */
	@TableField(value = "sku_pic_url")
	private String skuPicUrl;

	/** 已入库数量 */
	@TableField(value = "entry_count")
	private Integer entryCount;

	/** 小程序扫UPC强制通过理由 */
	@TableField(value = "upc_pass_reason")
	private String upcPassReason;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
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

	public String getBuyerTaskNo() {
		return this.buyerTaskNo;
	}

	public void setBuyerTaskNo(String buyerTaskNo) {
		this.buyerTaskNo = buyerTaskNo;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getUpc() {
		return this.upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public Integer getCurrency() {
		return this.currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getMode() {
		return this.mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getMaxCount() {
		return this.maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public String getSkuPicUrl() {
		return this.skuPicUrl;
	}

	public void setSkuPicUrl(String skuPicUrl) {
		this.skuPicUrl = skuPicUrl;
	}

	public Integer getEntryCount() {
		return this.entryCount;
	}

	public void setEntryCount(Integer entryCount) {
		this.entryCount = entryCount;
	}

	public String getUpcPassReason() {
		return this.upcPassReason;
	}

	public void setUpcPassReason(String upcPassReason) {
		this.upcPassReason = upcPassReason;
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
