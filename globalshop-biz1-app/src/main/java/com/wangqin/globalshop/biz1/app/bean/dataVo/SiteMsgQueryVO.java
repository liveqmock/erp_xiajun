package com.wangqin.globalshop.biz1.app.bean.dataVo;

/**
 * 站内信的查找
 * @author XiaJun
 *
 */
public class SiteMsgQueryVO extends PageQueryVO {
	private Long userId;
	
	private Long senderId;
	
	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	private Integer sendType;
	
	private Integer status;
	
	private Integer readStatus;
	
	private Integer isDel;
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}


	
	


	
	
}
