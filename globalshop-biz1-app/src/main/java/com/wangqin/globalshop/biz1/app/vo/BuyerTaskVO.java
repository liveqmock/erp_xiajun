package com.wangqin.globalshop.biz1.app.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;

public class BuyerTaskVO extends BuyerTaskDO{
	/**买手ID*/
	private Long buyerId;
	
	private String itemName;
	
	private String buySite;

    private String itemCode;
    
    private String upc;
    
    private String skuPicUrl;
    /**商品详情*/
    private String detailList;
    private String purOrderNo;
    private String remark;
    private String taskDesc;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskEndTime;
    private String taskTitle;
    
    public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBuySite() {
		return buySite;
	}

	public void setBuySite(String buySite) {
		this.buySite = buySite;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getDetailList() {
		return detailList;
	}

	public void setDetailList(String detailList) {
		this.detailList = detailList;
	}

	public String getPurOrderNo() {
		return purOrderNo;
	}

	public void setPurOrderNo(String purOrderNo) {
		this.purOrderNo = purOrderNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
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

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getSkuPicUrl() {
		return skuPicUrl;
	}

	public void setSkuPicUrl(String skuPicUrl) {
		this.skuPicUrl = skuPicUrl;
	}
	
}
