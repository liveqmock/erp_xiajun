package com.wangqin.globalshop.item.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemPackagePatternMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingPackingPatternDOMapperExt;
import com.wangqin.globalshop.biz1.app.dto.ItemPackagePatternDTO;
import com.wangqin.globalshop.biz1.app.vo.PackageLevelQueryVO;
import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.item.app.service.IItemPackagePatternService;

@Service
public class ItemPackagePatternServiceImpl implements IItemPackagePatternService {

    @Autowired
    private ShippingPackingPatternDOMapperExt packageLevelMapper;
    
    @Autowired
    private ItemPackagePatternMapperExt itemPackagePatternMapperExt;
    
  
    


    public Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO) {
        return packageLevelMapper.queryPackageLevelsCount(packageLevelQueryVO);
    }

    @Override
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
    public List<ShippingPackingPatternDO> queryPatternsByScaleNo(String no) {
    	return packageLevelMapper.queryPatternsByScaleNo(no);
    }

	

	@Override
	public ItemBrandDO selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
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
}
