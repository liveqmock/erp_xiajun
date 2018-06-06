package com.wangqin.globalshop.order.app.service.shipping;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface IShippingTrackService {

     List<ShippingTrackDO> queryShippingTrack(String logisticNo);

    void insert(ShippingTrackDO shippingTrackDO);

    void insertOrUpdateInfo();

    ShippingTrackDO selectByShippingOrderNo(ShippingTrackDO selShippingTrack);
    List<ShippingTrackDO> selectByShippingOrderNoList(String selShippingTrack);

    void update(ShippingTrackDO selectOne);

    int selectCount(ShippingTrackDO selShippingTrack);

    List<ShippingTrackDO> selectShippingTrackListByLogisticNo(String logisticNo);
}
