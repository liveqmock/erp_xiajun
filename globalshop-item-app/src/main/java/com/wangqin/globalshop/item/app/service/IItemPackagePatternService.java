package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PackageLevelQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingPackingPatternQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;



/**
 * 
 */

public interface IItemPackagePatternService {
	
	Integer countPatternsByScaleNo(String packagingScaleNo);

    JsonPageResult<List<ItemPackagePatternDTO>> queryPackageLevelList(PackageLevelQueryVO packageLevelQueryVO);

    void insertPattern(ShippingPackingPatternDO shippingPackingPatternDO);   

	void deleteById(Long id);
	
	void updateLevelSelectiveById(ItemPackagePatternDTO shippingPackingPatternDO);
}
