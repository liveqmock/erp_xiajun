package com.wangqin.globalshop.order.app.service.item.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.order.app.service.item.OrderItemSkuScaleService;

/**
 * 
 * @author XiaJun
 *
 */

@Service
public class OrderItemSkuScaleServiceImpl implements OrderItemSkuScaleService {

	@Autowired
	private ItemSkuScaleMapperExt itemSkuScaleMapperExt;
	
	//查询sku对应的所有规格以及规格的值
	@Override
	public List<ItemSkuScaleDO> selectScaleNameValueBySkuCode(String skuCode) {
		return itemSkuScaleMapperExt.selectScaleNameValueBySkuCode(skuCode);
	}
	
}
