package com.wangqin.globalshop.item.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingPackingPatternDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;
import com.wangqin.globalshop.item.app.service.IShippingPackingPatternService;

@Service
public class ShippingPackingPatternServiceImpl implements IShippingPackingPatternService {

    @Autowired
    private ShippingPackingPatternDOMapperExt packageLevelMapper;

    @Override
    public Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO) {
        return packageLevelMapper.queryPackageLevelsCount(packageLevelQueryVO);
    }

    @Override
    public JsonPageResult<List<ShippingPackingPatternDO>> queryPackageLevelList(ShippingPackingPatternQueryVO packageLevelQueryVO) {
        JsonPageResult<List<ShippingPackingPatternDO>> packageLevelResult = new JsonPageResult<>();

        Integer totalCount = packageLevelMapper.queryPackageLevelsCount(packageLevelQueryVO);

        if ((null != totalCount) && (0L != totalCount)) {
            packageLevelResult.buildPage(totalCount, packageLevelQueryVO);

            List<ShippingPackingPatternDO> packageLevels = packageLevelMapper.queryPackageLevels(packageLevelQueryVO);
            packageLevelResult.setData(packageLevels);
        } else {
            List<ShippingPackingPatternDO> packageLevels = new ArrayList<>();
            packageLevelResult.setData(packageLevels);
        }
        return packageLevelResult;
    }

    @Override
    public ShippingPackingPatternDO selectByPrimaryKey(Long id) {
        return packageLevelMapper.selectByPrimaryKey(id);

    }

}
