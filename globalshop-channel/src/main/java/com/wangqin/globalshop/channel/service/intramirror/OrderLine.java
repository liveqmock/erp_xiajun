package com.wangqin.globalshop.channel.service.intramirror;

import java.math.BigDecimal;

/**
 * Create by 777 on 2018/8/27
 */
public class OrderLine {

	private Long shop_product_sku_id;      // Long 商品skuid Y
	private Long product_id;            // String 商品id Y
	private Long category_id;          // String 类目id Y
	private Integer quantity;            // int 商品数量 Y
	private BigDecimal sale_price;      // Decimal C端商品销售金额，用来计算订单金额，会做校验，是否小于IM price Y
	public Long getShop_product_sku_id() {
		return shop_product_sku_id;
	}
	public void setShop_product_sku_id(Long shop_product_sku_id) {
		this.shop_product_sku_id = shop_product_sku_id;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getSale_price() {
		return sale_price;
	}
	public void setSale_price(BigDecimal sale_price) {
		this.sale_price = sale_price;
	}
}
