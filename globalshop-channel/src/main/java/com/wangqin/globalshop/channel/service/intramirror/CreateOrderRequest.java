package com.wangqin.globalshop.channel.service.intramirror;

import com.wangqin.globalshop.common.base.BaseDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by 777 on 2018/8/28
 */
public class CreateOrderRequest {
	private BigDecimal subtotalPrice;
	private Integer payWay;
	private Long user_id;
	private Long contact_phone;
	private String contact_name;
	private List<OrderLine> order_line;
	private CustomerInfo customer;
	private Long c_user_id;
	public BigDecimal getSubtotalPrice() {
		return subtotalPrice;
	}
	public void setSubtotalPrice(BigDecimal subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(Long contact_phone) {
		this.contact_phone = contact_phone;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public List<OrderLine> getOrder_line() {
		return order_line;
	}
	public void setOrder_line(List<OrderLine> order_line) {
		this.order_line = order_line;
	}
	public CustomerInfo getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}
	public Long getC_user_id() {
		return c_user_id;
	}
	public void setC_user_id(Long c_user_id) {
		this.c_user_id = c_user_id;
	}
}
