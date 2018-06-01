package com.wangqin.globalshop.common.utils;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = -3789653118513746686L;

	protected boolean success = false;

	protected String msg = "";

	protected T data = null;

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static JsonResult<Object> buildSuccess(Object data) {
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(true);
		result.setData(data);
		return result;
	}

	public static JsonResult<Object> buildFailed(String msg) {
		JsonResult<Object> result = new JsonResult<>();
		result.setSuccess(false);
		result.setMsg(msg);
		return result;
	}

	// 增加链式调用方法
	public JsonResult<T> buildData(T data) {
		this.data = data;
		return this;
	}

	public JsonResult<T> buildIsSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public JsonResult<T> buildMsg(String msg) {
		this.msg = msg;
		return this;
	}

}
