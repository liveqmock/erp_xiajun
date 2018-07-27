package com.wangqin.globalshop.biz1.app.bean.dataVo;

public class UploadUserInfo {

	private String userInfo;
	private String wxRawData;
	private String wxSignature;
	private String wxEncryptData;
	private String cookie;
	private String signature;
	private String wxIv;
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public String getWxRawData() {
		return wxRawData;
	}
	public void setWxRawData(String wxRawData) {
		this.wxRawData = wxRawData;
	}
	public String getWxSignature() {
		return wxSignature;
	}
	public void setWxSignature(String wxSignature) {
		this.wxSignature = wxSignature;
	}
	public String getWxEncryptData() {
		return wxEncryptData;
	}
	public void setWxEncryptData(String wxEncryptData) {
		this.wxEncryptData = wxEncryptData;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getWxIv() {
		return wxIv;
	}
	public void setWxIv(String wxIv) {
		this.wxIv = wxIv;
	}
	@Override
	public String toString() {
		return "UploadUserInfo [userInfo=" + userInfo + ", wxRawData=" + wxRawData + ", wxSignature=" + wxSignature
				+ ", wxEncryptData=" + wxEncryptData + ", cookie=" + cookie + ", signature=" + signature + ", wxIv="
				+ wxIv + "]";
	}
	
	
	

}
