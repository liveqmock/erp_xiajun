package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;

public interface ShippingPackingPatternDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShippingPackingPatternDO record);

    int insertSelective(ShippingPackingPatternDO record);

    ShippingPackingPatternDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShippingPackingPatternDO record);

    int updateByPrimaryKey(ShippingPackingPatternDO record);
}