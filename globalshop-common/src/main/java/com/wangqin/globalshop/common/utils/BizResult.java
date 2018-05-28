package com.wangqin.globalshop.common.utils;

/**
 * 
 * @author zhulu
 * service 业务返回结果信息。
 *
 */
public class BizResult {

	
	protected boolean success = false;

	protected String msg = "";
	
	
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

	public static BizResult buildSuccess() {
		BizResult result = new BizResult();
		result.setSuccess(true);
		return result;
	}

	public static BizResult buildFailed(String msg) {
		BizResult result = new BizResult();
		result.setSuccess(false);
		result.setMsg(msg);
		return result;
	}
}
