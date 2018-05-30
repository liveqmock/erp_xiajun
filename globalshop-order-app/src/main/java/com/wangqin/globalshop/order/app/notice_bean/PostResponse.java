package com.wangqin.globalshop.order.app.notice_bean;

public class PostResponse
{
	private Boolean result;
	/**
	 200: 提交成功
	 701: 拒绝订阅的快递公司
	 700: 订阅方的订阅数据存在错误（如不支持的快递公司、单号为空、单号超长等）
	 600: 您不是合法的订阅者（即授权Key出错）
	 500: 服务器错误
	 501: 重复订阅
	 */
	private String returnCode;
	private String message;
	
	public Boolean getResult()
	{
		return result;
	}
	
	public void setResult(Boolean result)
	{
		this.result = result;
	}
	
	public String getReturnCode()
	{
		return returnCode;
	}
	
	public void setReturnCode(String returnCode)
	{
		this.returnCode = returnCode;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString() {
		return "PostResponse [result=" + result + ", returnCode=" + returnCode + ", message=" + message + "]";
	}
	
	
	
}
