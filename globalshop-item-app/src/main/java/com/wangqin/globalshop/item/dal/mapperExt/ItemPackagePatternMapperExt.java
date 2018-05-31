package com.wangqin.globalshop.item.dal.mapperExt;

import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.item.app.dal.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.item.app.vo.PackageLevelQueryVO;


public interface ItemPackagePatternMapperExt  {

	List<ItemPackagePatternDTO> queryPatternsByScaleNo(String no);
	
	Integer queryPackageLevelsCount(PackageLevelQueryVO packageLevelQueryVO);
	
	List<ItemPackagePatternDTO> queryPackageLevels(PackageLevelQueryVO packageLevelQueryVO);
	
	void insertPattern(ShippingPackingPatternDO shippingPackingPatternDO);
	
	void deleteById(Long id);
}
