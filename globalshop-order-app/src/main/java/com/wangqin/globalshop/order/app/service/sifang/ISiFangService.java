package com.wangqin.globalshop.order.app.service.sifang;

import java.text.ParseException;

public interface ISiFangService {

	String createOrder(String shippingOrderNO);
	
	public void shippingTrack(String shipperOrderNo) throws ParseException;
}
