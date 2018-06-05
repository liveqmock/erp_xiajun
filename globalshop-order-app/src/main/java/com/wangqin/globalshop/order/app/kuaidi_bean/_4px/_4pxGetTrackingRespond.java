package com.wangqin.globalshop.order.app.kuaidi_bean._4px;

public class _4pxGetTrackingRespond {

	private String ResponseCode;

	private String Message;

	private _4pxGetTrackingData Data;

	public void setResponseCode(String ResponseCode){
		this.ResponseCode = ResponseCode;
	}
	
	public String getResponseCode(){
		return this.ResponseCode;
	}
	
	public void setMessage(String Message){
		this.Message = Message;
	}
	
	public String getMessage(){
		return this.Message;
	}
	
	public void setData(_4pxGetTrackingData Data){
		this.Data = Data;
	}
	
	public _4pxGetTrackingData getData(){
		return this.Data;
	}

}
