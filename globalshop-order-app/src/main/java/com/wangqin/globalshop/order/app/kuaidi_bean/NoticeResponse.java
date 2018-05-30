package com.wangqin.globalshop.order.app.kuaidi_bean;

public class NoticeResponse {
	private Boolean result;
	private String returnCode;
	private String message;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toJson() {
		return "{\"result\":\""+result+"\",\"returnCode\":\""+returnCode+"\",\"message\":\""+message+"\"}";
	}
	
	public static void main(String[] args) {
		NoticeResponse response = new NoticeResponse();
		response.setMessage("保存成功");
		response.setResult(true);
		response.setReturnCode("200");
		System.out.println(response.toJson());
	}
}
