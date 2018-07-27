package com.wangqin.globalshop.order.app.service.shipping;

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
		/*List<CommonShippingTrack> shippingTrackList = shippingTrackMapper.queryStatus();

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

    @Override
    public ShippingTrackDO selectByShippingOrderNo(ShippingTrackDO selShippingTrack) {
        return shippingTrackDOMapper.selectByShippingOrderNo(selShippingTrack.getShippingOrderNo());
    }

    @Override
    public List<ShippingTrackDO> selectByShippingOrderNoList(String selShippingTrack) {
        return shippingTrackDOMapper.selectByShippingOrderNoList(selShippingTrack);
    }

    @Override
    public void update(ShippingTrackDO selectOne) {
        shippingTrackDOMapper.updateByPrimaryKey(selectOne);
    }

    @Override
    public int selectCount(ShippingTrackDO selShippingTrack) {
        return shippingTrackDOMapper.selectCountByShippingOrderNo(selShippingTrack.getShippingOrderNo());
    }

    @Override
    public List<ShippingTrackDO> selectShippingTrackListByLogisticNo(String logisticNo){
        return shippingTrackDOMapper.selectShippingTrackListByLogisticNo(logisticNo);
    }

    @Override
    public ShippingTrackDO getByLogisticNoAndLogisticsStatus(String logisticNo, Integer logisticsStatus) {
        return shippingTrackDOMapper.getByLogisticNoAndLogisticsStatus(logisticNo, logisticsStatus);
    }

}
