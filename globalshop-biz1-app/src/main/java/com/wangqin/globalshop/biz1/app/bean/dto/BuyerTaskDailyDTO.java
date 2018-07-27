package com.wangqin.globalshop.biz1.app.bean.dto;

import java.util.Date;

/**
 * 买家查询自己所有的待完成子任务
 * @author zhulu
 *
 */
public class BuyerTaskDailyDTO {
//	
//	SKU代码 sku_code
//	商品名称 item_name
//	颜色 color
//	尺寸 scale
//	UPC
//	图片 sku_pic
//	采购数量 count
//	最大采购数量 task_max_count
//	开始时间 task_start_time
//	结束时间 task_end_time
//	采购价格 task_price
//	最高采购价 task_max_price
	//任务明细ID
	private Long taskDetailId;
	
	private Long skuId;
	
	private String skuCode;
	
	private String itemName;
	
	private String color;
	
	private String scale;
	
	private String buySite;
	
	private String upc;
	
	private String skuPic;
	
	private Integer count;
	
	private Integer taskMaxCount;
	
	private Date taskStartTime;

	private Date taskEndTime;
	
	private Double taskPrice;
	
	private Double taskMaxPrice;
	
	private Integer inCount; //已入库数量
	
	private Integer taskDailyCount;
	
	private Integer taskDailyDetailId;
	
	private Double costPrice;
	
	private Double discount;
	
	private Double purchasePrice;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getSkuPic() {
		return skuPic;
	}

	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTaskMaxCount() {
		return taskMaxCount;
	}

	public void setTaskMaxCount(Integer taskMaxCount) {
		this.taskMaxCount = taskMaxCount;
	}

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public Double getTaskPrice() {
		return taskPrice;
	}

	public void setTaskPrice(Double taskPrice) {
		this.taskPrice = taskPrice;
	}

	public Double getTaskMaxPrice() {
		return taskMaxPrice;
	}

	public void setTaskMaxPrice(Double taskMaxPrice) {
		this.taskMaxPrice = taskMaxPrice;
	}

	public Long getTaskDetailId() {
		return taskDetailId;
	}

	public void setTaskDetailId(Long taskDetailId) {
		this.taskDetailId = taskDetailId;
	}

	public Integer getInCount() {
		return inCount;
	}

	public void setInCount(Integer inCount) {
		this.inCount = inCount;
	}

	public Integer getTaskDailyCount() {
		return taskDailyCount;
	}

	public void setTaskDailyCount(Integer taskDailyCount) {
		this.taskDailyCount = taskDailyCount;
	}

	public Integer getTaskDailyDetailId() {
		return taskDailyDetailId;
	}

	public void setTaskDailyDetailId(Integer taskDailyDetailId) {
		this.taskDailyDetailId = taskDailyDetailId;
	}

	public String getBuySite() {
		return buySite;
	}

	public void setBuySite(String buySite) {
		this.buySite = buySite;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
}
