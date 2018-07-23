package com.wangqin.globalshop.order.app.service.shipping.sifang;

import java.text.ParseException;

public interface ISiFangService {

	String createOrder(String shippingOrderNO);
	
	public void shippingTrack(String shipperOrderNo) throws ParseException;
}
