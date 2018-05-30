package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.item.app.dal.dto.ItemPackageScaleDTO;
import com.wangqin.globalshop.item.app.service.IItemPackageScaleService;
import com.wangqin.globalshop.item.dal.mapperExt.ItemPackagePatternMapperExt;
import com.wangqin.globalshop.item.dal.mapperExt.ItemPackageScaleMapperExt;

@Service
public class ItemPackageScaleServiceImpl implements IItemPackageScaleService{

	@Autowired
	private ItemPackageScaleMapperExt packageScaleMapperExt;
	
	@Autowired
	private ItemPackagePatternMapperExt shippingPackingPatternDOMapperExt;
	
	//public List<ItemPackageScaleDTO> queryPackageScales() {
		//return packageScaleMapperExt.queryPackageScales();
	//}
	
	@Override
	public JsonPageResult<List<ItemPackageScaleDTO>>  queryPackageScaleTree() {
		JsonPageResult<List<ItemPackageScaleDTO>> result = new JsonPageResult<>();
		
		List<ItemPackageScaleDTO> packageScales = packageScaleMapperExt.queryAllPackageScale();
		
		for (ItemPackageScaleDTO packageScale : packageScales) {
			packageScale.setPackageLevels(shippingPackingPatternDOMapperExt.queryPatternsByScaleNo(packageScale.getPackagingScaleNo()));
		}
		
		result.setData(packageScales);
		
		return result;
	}
}
