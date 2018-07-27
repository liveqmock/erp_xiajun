package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.Date;

public class TaskDailyOrderUncompleteQueryVO extends PageQueryVO {
	
	private Date startTime;

	private Date endTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
