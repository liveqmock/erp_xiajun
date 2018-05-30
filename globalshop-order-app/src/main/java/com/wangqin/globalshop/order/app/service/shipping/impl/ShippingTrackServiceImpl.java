package com.wangqin.globalshop.order.app.service.shipping.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingTrackDOMapperExt;
import com.wangqin.globalshop.order.app.service.shipping.IShippingTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class ShippingTrackServiceImpl implements IShippingTrackService {
    @Autowired
    private ShippingTrackDOMapperExt shippingTrackDOMapper;

    @Override
    public List<ShippingTrackDO> queryShippingTrack(String logisticNo) {
        return shippingTrackDOMapper.queryShippingTrack(logisticNo);
    }

    @Override
    public void insert(ShippingTrackDO shippingTrackDO) {
        shippingTrackDOMapper.insert(shippingTrackDO);

    }

    @Override
    public void insertOrUpdateInfo() {
		/*List<ShippingTrack> shippingTrackList = shippingTrackMapper.queryStatus();

		shippingTrackList.forEach(shippingTrack -> {
			if (StringUtil.isNotBlank(shippingTrack.getInlandExpressId())
					&& StringUtil.isNotBlank(shippingTrack.getInlandExpressNo())
					&& !shippingTrack.getInlandExpressId().contains("haihu")) {
				String info = ExpressUtil.callbackLogisticsTrajectory(shippingTrack.getInlandExpressId(),
						shippingTrack.getInlandExpressNo());
				if (info.contains("签收")) {
					shippingOrderMapper.updateStatusByShippingNo(shippingTrack.getShippingNo());
				}
				shippingTrackMapper.updateInfo(info, shippingTrack.getShippingNo());
			}
		});*/
    }

}
