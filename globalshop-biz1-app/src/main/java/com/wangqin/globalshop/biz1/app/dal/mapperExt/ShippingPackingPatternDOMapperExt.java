package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingPackingPatternDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ShippingPackingPatternQueryVO;

import java.util.List;

public interface ShippingPackingPatternDOMapperExt extends ShippingPackingPatternDOMapper {

    Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO);

    List<ShippingPackingPatternDO> queryPackageLevels(ShippingPackingPatternQueryVO packageLevelQueryVO);

    List<ShippingPackingPatternDO> queryPatternsByScaleNo(String no);

    ShippingPackingPatternDO selectByName(String packName);
}
