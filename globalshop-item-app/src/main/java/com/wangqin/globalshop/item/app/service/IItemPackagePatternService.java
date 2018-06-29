package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.biz1.app.vo.PackageLevelQueryVO;
import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;



/**
 * 
 */

public interface IItemPackagePatternService {
	
	Integer countPatternsByScaleNo(String packagingScaleNo);
	
    Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO);

    // Boolean updatePackageScaleByEnName(Long packageId, String newEnName);

   

    ItemBrandDO selectByPrimaryKey(Long id);


    
    List<ShippingPackingPatternDO> queryPatternsByScaleNo(String no);
    
    JsonPageResult<List<ItemPackagePatternDTO>> queryPackageLevelList(PackageLevelQueryVO packageLevelQueryVO);

    void insertPattern(ShippingPackingPatternDO shippingPackingPatternDO);
    

	void deleteById(Long id);
	
	void updateLevelSelectiveById(ItemPackagePatternDTO shippingPackingPatternDO);
}
