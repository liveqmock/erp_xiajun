package com.wangqin.globalshop.channel.service.intramirror;

/**
 * Create by 777 on 2018/8/28
 */
public class CustomerOrder {

	private Integer status;
	private Long order_id;
	private String order_num;//用来查物流
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
}
