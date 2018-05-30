package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.vo.ItemBrandQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;


/**
 * 
 */

public interface IItemPackagePatternService {

    Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO);

    JsonPageResult<List<ShippingPackingPatternDO>> queryPackageLevelList(ShippingPackingPatternQueryVO packageLevelQueryVO);

    // Boolean updatePackageScaleByEnName(Long packageId, String newEnName);

    void add(ItemBrandDO itemBrand);

    void addBrand(ItemBrandDO brand);

    void updateBrand(ItemBrandDO brand);

    // 分页查询
    JsonPageResult<List<ItemBrandDO>> queryBrands(ItemBrandQueryVO brandQueryVO);

    // 总查询
    JsonPageResult<List<ItemBrandDO>> queryAllBrand();

   

    ItemBrandDO selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);
    
    List<ShippingPackingPatternDO> queryPatternsByScaleNo(String no);

}
