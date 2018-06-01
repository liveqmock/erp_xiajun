package com.wangqin.globalshop.order.app.service.sifang.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackPolling4pxDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingTrackPolling4pxDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingTrackPolling4pxDOMapperExt;
import com.wangqin.globalshop.order.app.service.sifang.OrderShippingTrackPolling4pxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderShippingTrackPolling4pxServiceImpl implements OrderShippingTrackPolling4pxService {
	@Autowired
	private ShippingTrackPolling4pxDOMapperExt mapper;

	@Override
	public ShippingTrackPolling4pxDO selectByShippingOrderNo(String shipperOrderNo) {
		return mapper.selectByShippingOrderNo(shipperOrderNo);
	}

	@Override
	public void insert(ShippingTrackPolling4pxDO pxon) {
		mapper.insert(pxon);

	}

	@Override
	public void update(ShippingTrackPolling4pxDO pxon) {
		mapper.updateByPrimaryKey(pxon);

	}
}
