package com.wangqin.globalshop.common.utils;

import java.io.Serializable;

public class EasyuiJsonResult<T> implements Serializable {

	private static final long serialVersionUID = -3789653118513746686L;

	protected boolean success = false;

	protected String msg = "";

	protected T rows = null;

	protected Integer total;
	
	protected boolean productRoler = false;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	
	public boolean isProductRoler() {
		return productRoler;
	}

	public void setProductRoler(boolean productRoler) {
		this.productRoler = productRoler;
	}

	// 增加链式调用方法
	public EasyuiJsonResult<T> buildRows(T rows) {
		this.rows = rows;
		return this;
	}
	public EasyuiJsonResult<T> buildIsSuccess(boolean success) {
		this.success = success;
		return this;
	}
	public EasyuiJsonResult<T> buildMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getRows() {
		return rows;
	}

	public void setRows(T rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}