package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackPollingYuntongDO;

public interface ShippingTrackPollingYuntongDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShippingTrackPollingYuntongDO record);

    int insertSelective(ShippingTrackPollingYuntongDO record);

    ShippingTrackPollingYuntongDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShippingTrackPollingYuntongDO record);

    int updateByPrimaryKey(ShippingTrackPollingYuntongDO record);
}