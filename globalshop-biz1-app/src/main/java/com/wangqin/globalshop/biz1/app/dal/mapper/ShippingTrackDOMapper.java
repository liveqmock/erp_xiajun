package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;

public interface ShippingTrackDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShippingTrackDO record);

    int insertSelective(ShippingTrackDO record);

    ShippingTrackDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShippingTrackDO record);

    int updateByPrimaryKey(ShippingTrackDO record);
}