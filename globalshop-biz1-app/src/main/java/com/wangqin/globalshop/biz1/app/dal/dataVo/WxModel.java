package com.wangqin.globalshop.biz1.app.dal.dataVo;

public class WxModel {
	private String op;
	private String code;
	private String productId;
	private String productVersion;
	private String systemInfo;
	private String cookie;
	private WxSystemInfo wxSystemInfo;
	
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductVersion() {
		return productVersion;
	}
	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}
	public String getSystemInfo() {
		return systemInfo;
	}
	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}
	
	public WxSystemInfo getWxSystemInfo() {
		return wxSystemInfo;
	}
	public void setWxSystemInfo(WxSystemInfo wxSystemInfo) {
		this.wxSystemInfo = wxSystemInfo;
	}
	@Override
	public String toString() {
		return "WxModel [op=" + op + ", code=" + code + ", productId=" + productId + ", productVersion="
				+ productVersion + ", systemInfo=" + systemInfo + "]";
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
}
