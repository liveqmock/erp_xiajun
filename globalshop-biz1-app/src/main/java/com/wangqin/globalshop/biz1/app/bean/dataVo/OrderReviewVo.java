package com.wangqin.globalshop.biz1.app.bean.dataVo;

public class OrderReviewVo {
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 审核动作， 审核通过/审核拒绝
	 */
	private Integer action;
	/**
	 * 审核意见
	 */
	private String comments;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
