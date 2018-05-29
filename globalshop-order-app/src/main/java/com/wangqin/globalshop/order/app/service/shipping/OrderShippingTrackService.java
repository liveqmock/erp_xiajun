package com.wangqin.globalshop.order.app.service.shipping;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface OrderShippingTrackService {

    List<ShippingTrackDO> queryShippingTrack(String logisticNo);
}
