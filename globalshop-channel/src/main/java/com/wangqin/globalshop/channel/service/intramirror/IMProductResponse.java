package com.wangqin.globalshop.channel.service.intramirror;

import java.util.List;

/**
 * Create by 777 on 2018/8/27
 */
public class IMProductResponse {

	private List<IMProduct> productList;

	private Integer status;


	public List<IMProduct> getProductList() {
		return productList;
	}
	public void setProductList(List<IMProduct> productList) {
		this.productList = productList;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
