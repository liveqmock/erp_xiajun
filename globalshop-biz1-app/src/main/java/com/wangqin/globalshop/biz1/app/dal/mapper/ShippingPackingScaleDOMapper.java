package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingScaleDO;

public interface ShippingPackingScaleDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShippingPackingScaleDO record);

    int insertSelective(ShippingPackingScaleDO record);

    ShippingPackingScaleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShippingPackingScaleDO record);

    int updateByPrimaryKey(ShippingPackingScaleDO record);
}