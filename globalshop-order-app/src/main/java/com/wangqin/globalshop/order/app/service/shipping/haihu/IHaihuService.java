package com.wangqin.globalshop.order.app.service.shipping.haihu;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;

import java.text.ParseException;

public interface IHaihuService {

	void createOrder(Long id);

	void shippingTrack(String shipperOrderNo) throws ParseException;
	
	void returnPackageNo(ShippingOrderDO shippingOrder);
}
