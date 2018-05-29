package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingScaleDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.PackageLevelQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.PackageScaleMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingPackingPatternDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.item.app.service.IShippingPackingScaleService;

@Service
public class ShippingPackingScaleServiceImplement implements IShippingPackingScaleService{

	@Autowired
	private PackageScaleMapperExt packageScaleMapperExt;
	
	@Autowired
	private ShippingPackingPatternDOMapperExt shippingPackingPatternDOMapperExt;
	
	public List<ShippingPackingScaleDO> queryPackageScales() {
		return packageScaleMapperExt.queryPackageScales();
	}
	
	@Override
	public JsonPageResult<List<ShippingPackingScaleDO>> queryPackageScaleTree() {
		JsonPageResult<List<ShippingPackingScaleDO>> result = new JsonPageResult<>();
		
		List<ShippingPackingScaleDO> packageScales = packageScaleMapperExt.queryPackageScales();
		
		for (ShippingPackingScaleDO packageScale : packageScales) {
			ShippingPackingPatternQueryVO packageLevelQueryVO = new ShippingPackingPatternQueryVO();
			packageLevelQueryVO.setFirstStart(0);
			packageLevelQueryVO.setPackageEn(packageScale.getNameEn());
			//packageScale.setPackageLevels(shippingPackingPatternDOMapperExt.queryPackageLevels(packageLevelQueryVO));
		}
		
		result.setData(packageScales);
		
		return result;
	}
}
