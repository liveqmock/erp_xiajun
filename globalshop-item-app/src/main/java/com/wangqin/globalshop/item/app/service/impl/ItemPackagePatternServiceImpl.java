package com.wangqin.globalshop.item.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemPackagePatternMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingPackingPatternDOMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PackageLevelQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingPackingPatternQueryVO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.item.app.service.IItemPackagePatternService;

@Service
public class ItemPackagePatternServiceImpl implements IItemPackagePatternService {

    @Autowired
    private ItemPackagePatternMapperExt itemPackagePatternMapperExt;

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public JsonPageResult<List<ItemPackagePatternDTO>> queryPackageLevelList(PackageLevelQueryVO packageLevelQueryVO) {
        JsonPageResult<List<ItemPackagePatternDTO>> packageLevelResult = new JsonPageResult<>();

        Integer totalCount = itemPackagePatternMapperExt.queryPackageLevelsCount(packageLevelQueryVO);

        if ((null != totalCount) && (0L != totalCount)) {
            packageLevelResult.buildPage(totalCount, packageLevelQueryVO);

            List<ItemPackagePatternDTO> packageLevels = itemPackagePatternMapperExt.queryPackageLevels(packageLevelQueryVO);
            packageLevelResult.setData(packageLevels);
        } else {
            List<ItemPackagePatternDTO> packageLevels = new ArrayList<>();
            packageLevelResult.setData(packageLevels);
        }
        return packageLevelResult;
    }


	@Override
	public void insertPattern(ShippingPackingPatternDO shippingPackingPatternDO) {
		itemPackagePatternMapperExt.insertPattern(shippingPackingPatternDO);
	}
   
	
	@Override
	public void deleteById(Long id) {
		itemPackagePatternMapperExt.deleteById(id);
	}

	
	@Override
	public void updateLevelSelectiveById(ItemPackagePatternDTO shippingPackingPatternDO) {
		itemPackagePatternMapperExt.updateLevelSelectiveById(shippingPackingPatternDO);
	}

	@Override
	public Integer countPatternsByScaleNo(String packagingScaleNo) {
		// TODO Auto-generated method stub
		return itemPackagePatternMapperExt.countPatternsByScaleNo(packagingScaleNo);
	}
	
}
