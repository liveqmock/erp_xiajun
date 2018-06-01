package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;

public interface CAShippingPackingPatternDOMapperExt {

      Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO);
	
	List<ShippingPackingPatternDO> queryPackageLevels(ShippingPackingPatternQueryVO packageLevelQueryVO);
	
	
}
