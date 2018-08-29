package com.wangqin.globalshop.channel.service.intramirror;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by 777 on 2018/8/28
 */
public class OrderdetailResponse {

	private Integer status;

	private BigDecimal exchangerRate;

	private Integer orderStatus;

	private List<CustomerOrder> customerOrderList;

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getExchangerRate() {
		return exchangerRate;
	}
	public void setExchangerRate(BigDecimal exchangerRate) {
		this.exchangerRate = exchangerRate;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<CustomerOrder> getCustomerOrderList() {
		return customerOrderList;
	}
	public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
		this.customerOrderList = customerOrderList;
	}
}
