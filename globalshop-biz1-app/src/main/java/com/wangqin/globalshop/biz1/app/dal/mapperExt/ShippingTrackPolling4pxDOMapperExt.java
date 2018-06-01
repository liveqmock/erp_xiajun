package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackPolling4pxDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingTrackPolling4pxDOMapper;

public interface ShippingTrackPolling4pxDOMapperExt extends ShippingTrackPolling4pxDOMapper {

    ShippingTrackPolling4pxDO selectByShippingOrderNo(String shipperOrderNo);
}