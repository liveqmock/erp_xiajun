package com.wangqin.globalshop.biz1.app.dal.dataVo;

import java.util.Date;

/**
 * 采购单查询对象
 * @author zhulu
 *
 */
public class PurchaseQueryVO extends PageQueryVO{

	private String taskTitle;
	
	private Long ownerId;
	
	private Long buyerId;
	
	//任务开始日期
	private Date taskStart1;
	
	private Date taskStart2;
	
	//任务结束日期
	private Date taskEnd1;
	
	private Date taskEnd2;
	
	
	private String taskOrderNo;//采购任务单
	
	private Integer status;
	
	private Long companyId;

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}




	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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

	public String getTaskOrderNo() {
		return taskOrderNo;
	}

	public void setTaskOrderNo(String taskOrderNo) {
		this.taskOrderNo = taskOrderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	
}
