package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.biz1.app.vo.PackageLevelQueryVO;import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;



public interface ItemPackagePatternMapperExt  {

	List<ItemPackagePatternDTO> queryPatternsByScaleNo(String no);
	
	Integer queryPackageLevelsCount(PackageLevelQueryVO packageLevelQueryVO);
	
	List<ItemPackagePatternDTO> queryPackageLevels(PackageLevelQueryVO packageLevelQueryVO);
	
	void insertPattern(ShippingPackingPatternDO shippingPackingPatternDO);
	
	void deleteById(Long id);
	
	void updateLevelSelectiveById(ItemPackagePatternDTO itemPackagePatternDTO);
	
	
}
