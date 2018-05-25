package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;



public interface ShippingPackingPatternDOMapperExt {

      Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO);
	
	List<ShippingPackingPatternDO> queryPackageLevels(ShippingPackingPatternQueryVO packageLevelQueryVO);
	
	
}
