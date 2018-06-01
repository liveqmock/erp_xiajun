package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingTrackDOMapper;

import java.util.List;

public interface ShippingTrackDOMapperExt extends ShippingTrackDOMapper {

    List<ShippingTrackDO> queryShippingTrack(String logisticNo);
}