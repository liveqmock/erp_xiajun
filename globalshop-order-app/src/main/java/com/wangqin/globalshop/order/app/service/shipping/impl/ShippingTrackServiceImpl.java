package com.wangqin.globalshop.order.app.service.shipping.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingTrackDOMapperExt;
import com.wangqin.globalshop.order.app.service.shipping.ShippingTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class ShippingTrackServiceImpl implements ShippingTrackService {
    @Autowired
    private ShippingTrackDOMapperExt shippingTrackDOMapper;

    @Override
    public List<ShippingTrackDO> queryShippingTrack(String logisticNo) {
        return shippingTrackDOMapper.queryShippingTrack(logisticNo);
    }

    @Override
    public void insertOrUpdateInfo() {

    }
}
