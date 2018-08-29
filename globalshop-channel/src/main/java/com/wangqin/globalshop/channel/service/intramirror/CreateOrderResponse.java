package com.wangqin.globalshop.channel.service.intramirror;

/**
 * Create by 777 on 2018/8/28
 */
public class CreateOrderResponse {

	private Integer status;// int 返回状态 1：成功、-1014：参数格式错误、-1005：用户不存在、-6024：库存不足、其他：系统异常
	private Long order_id;// Long 订单id
	private Long order_logistics_id;// Long 订单物流信息id

	private String lacking_products;//缺货信息

	private String info;

	private String msg;

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
	public Long getOrder_logistics_id() {
		return order_logistics_id;
	}
	public void setOrder_logistics_id(Long order_logistics_id) {
		this.order_logistics_id = order_logistics_id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getLacking_products() {
		return lacking_products;
	}
	public void setLacking_products(String lacking_products) {
		this.lacking_products = lacking_products;
	}
}
