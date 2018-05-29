package com.wangqin.globalshop.item.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.item.app.service.IFreightService;
import com.wangqin.globalshop.item.app.service.IShippingPackingPatternService;



@Service
public class FreightServiceImpl  implements IFreightService {
	
	@Autowired
	private IShippingPackingPatternService iPackageLevelService;
	
	@Override
	public Long calculateFreight(Double itemWeight, Double packageWeight) {
		Long fee = 3500l;
		double d = (itemWeight+packageWeight -0.5)/0.1d;
		long a = (long)Math.ceil(d);
		if(d >= 0){
			fee += a*500;
		}
		return fee;
	}
	
	
	@Override
	public ShippingPackingPatternDO getPackageLevel(Long id) {
		return null;
	}

	
}
