package com.wangqin.globalshop.order.app.kuaidi_bean._4px;

/**
 * 预报时4px(四方)订单
 * @author kevan
 *
 */
public class _4pxOrder {
	private String Token ;//Token由转运四方提供
	/*
	 *	Data业务数据部分 
	*/
	private _4pxOrderData Data  ;
	
	
	public _4pxOrderData getData() {
		return Data;
	}
	public void setData(_4pxOrderData data) {
		this.Data = data;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		this.Token = token;
	}
}
