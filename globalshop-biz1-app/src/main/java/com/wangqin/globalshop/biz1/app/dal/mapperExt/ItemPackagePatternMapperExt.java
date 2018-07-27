package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PackageLevelQueryVO;


public interface ItemPackagePatternMapperExt  {
	Integer countPatternsByScaleNo(String packagingScaleNo);
	
	List<ItemPackagePatternDTO> queryPatternsByScaleNo(String no);
	
	Integer queryPackageLevelsCount(PackageLevelQueryVO packageLevelQueryVO);
	
	List<ItemPackagePatternDTO> queryPackageLevels(PackageLevelQueryVO packageLevelQueryVO);
	
	void insertPattern(ShippingPackingPatternDO shippingPackingPatternDO);
	
	void deleteById(Long id);
	
	void updateLevelSelectiveById(ItemPackagePatternDTO itemPackagePatternDTO);
	
	
}
