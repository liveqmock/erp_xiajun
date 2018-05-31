package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;

import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.item.app.dal.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.item.app.vo.PackageLevelQueryVO;


/**
 * 
 */

public interface IItemPackagePatternService {

    Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO);

   

    // Boolean updatePackageScaleByEnName(Long packageId, String newEnName);

   

    ItemBrandDO selectByPrimaryKey(Long id);


    
    List<ShippingPackingPatternDO> queryPatternsByScaleNo(String no);
    
    JsonPageResult<List<ItemPackagePatternDTO>> queryPackageLevelList(PackageLevelQueryVO packageLevelQueryVO);

    void insertPattern(ShippingPackingPatternDO shippingPackingPatternDO);
    

	void deleteById(Long id);
}
