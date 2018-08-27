package com.wangqin.globalshop.channel.service.intramirror;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Create by 777 on 2018/8/27
 */
public class IMSku {


	private String skuid;
	private String size;//尺寸
	private Integer stock;//库存
	private BigDecimal retail_price;//建议售价
	private BigDecimal im_price;//IM售卖价格
	private BigDecimal shipping_fee;//运费
	private BigDecimal tax;
	private String updated_at;


	public String getSkuid() {
		return skuid;
	}
	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public BigDecimal getRetail_price() {
		return retail_price;
	}
	public void setRetail_price(BigDecimal retail_price) {
		this.retail_price = retail_price;
	}
	public BigDecimal getIm_price() {
		return im_price;
	}
	public void setIm_price(BigDecimal im_price) {
		this.im_price = im_price;
	}
	public BigDecimal getShipping_fee() {
		return shipping_fee;
	}
	public void setShipping_fee(BigDecimal shipping_fee) {
		this.shipping_fee = shipping_fee;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
}
