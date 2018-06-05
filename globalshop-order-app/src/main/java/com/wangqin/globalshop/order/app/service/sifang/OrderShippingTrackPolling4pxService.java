package com.wangqin.globalshop.order.app.service.sifang;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackPolling4pxDO;

public interface OrderShippingTrackPolling4pxService {

	ShippingTrackPolling4pxDO selectByShippingOrderNo(String shipperOrderNo);

	void insert(ShippingTrackPolling4pxDO pxon);

	void update(ShippingTrackPolling4pxDO pxon);
}
