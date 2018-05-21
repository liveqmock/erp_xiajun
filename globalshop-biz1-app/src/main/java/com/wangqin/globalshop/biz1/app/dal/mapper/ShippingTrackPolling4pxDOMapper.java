package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackPolling4pxDO;

public interface ShippingTrackPolling4pxDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShippingTrackPolling4pxDO record);

    int insertSelective(ShippingTrackPolling4pxDO record);

    ShippingTrackPolling4pxDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShippingTrackPolling4pxDO record);

    int updateByPrimaryKey(ShippingTrackPolling4pxDO record);
}