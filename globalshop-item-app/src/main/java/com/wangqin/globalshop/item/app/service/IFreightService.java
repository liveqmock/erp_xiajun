package com.wangqin.globalshop.item.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;


public interface IFreightService {
	/**
	 * 根据商品重量和包装重量计算运费
	 * @param itemWeight unit g
	 * @param packageWeight unit g
	 * @return
	 */
	Long calculateFreight(Double itemWeight,Double packageWeight);
	
	ShippingPackingPatternDO getPackageLevel(Long id);
}
