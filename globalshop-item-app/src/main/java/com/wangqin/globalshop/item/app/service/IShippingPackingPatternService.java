package com.wangqin.globalshop.item.app.service;


import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;



/**
 * 
 */


public interface IShippingPackingPatternService {


Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO);
	
	JsonPageResult<List<ShippingPackingPatternDO>> queryPackageLevelList(ShippingPackingPatternQueryVO packageLevelQueryVO);
	
	
	//Boolean updatePackageScaleByEnName(Long packageId, String newEnName);
	
	//替代原先的mybaits-plus里的方法
	ShippingPackingPatternDO selectByPrimaryKey(Long id);
}
