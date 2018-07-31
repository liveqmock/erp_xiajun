package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerTaskDetailVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class BuyerTaskVO extends BuyerTaskDO{
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

	/**
	 * 任务单号
	 */
	private String taskOrderNo;
	/**
	 * 任务名称
	 */
	private String taskTitle;
	/**
	 * 买手 ID
	 */
	private Long buyerId;
	/**
	 * 买手名称
	 */
	private String buyerName;
	/**
	 * 开始时间范围
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date taskStart1;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date taskStart2;

	/**
	 * 结束时间范围
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date taskEnd1;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date taskEnd2;

    private List<BuyerTaskDetailVO> taskDetailList;
	public List<BuyerTaskDetailVO> getTaskDetailList() {
		return taskDetailList;
	}
	public void setTaskDetailList(List<BuyerTaskDetailVO> taskDetailList) {
		this.taskDetailList = taskDetailList;
	}
	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	@Override
	public String getBuyerName() {
		return buyerName;
	}

	@Override
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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

	public String getTaskOrderNo() {
		return taskOrderNo;
	}

	public void setTaskOrderNo(String taskOrderNo) {
		this.taskOrderNo = taskOrderNo;
	}

	public Date getTaskStart1() {
		return taskStart1;
	}

	public void setTaskStart1(Date taskStart1) {
		this.taskStart1 = taskStart1;
	}

	public Date getTaskStart2() {
		return taskStart2;
	}

	public void setTaskStart2(Date taskStart2) {
		this.taskStart2 = taskStart2;
	}

	public Date getTaskEnd1() {
		return taskEnd1;
	}

	public void setTaskEnd1(Date taskEnd1) {
		this.taskEnd1 = taskEnd1;
	}

	public Date getTaskEnd2() {
		return taskEnd2;
	}

	public void setTaskEnd2(Date taskEnd2) {
		this.taskEnd2 = taskEnd2;
	}
}
