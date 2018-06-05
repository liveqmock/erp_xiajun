package com.wangqin.globalshop.order.app.kuaidi_bean._4px;

public class _4pxResponse {
	public final static String SUCCESS = "10000" ;//成功
	public final static String ItemDeclareType = "11307" ;//禁运
	
	private String ResponseCode ;
	private String Message ;
	private String Data ;
	
	public String getResponseCode() {
		return ResponseCode;
	}
	public void setResponseCode(String responseCode) {
		ResponseCode = responseCode;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	
	public _4pxResponse(String ResponseCode,String Message,String Data){
		this.ResponseCode = ResponseCode ;
		this.Message = Message ;
		this.Data = Data ;
	}
}
