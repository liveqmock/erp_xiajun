package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingTrackDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingTrackDOMapperExt extends ShippingTrackDOMapper {

    List<ShippingTrackDO> queryShippingTrack(String logisticNo);

    ShippingTrackDO selectByShippingOrderNo(String shippingOrderNo);

    int selectCountByShippingOrderNo(String shippingOrderNo);

    List<ShippingTrackDO> selectByShippingOrderNoList(String selShippingTrack);

    List<ShippingTrackDO> selectShippingTrackListByLogisticNo(String logisticNo);

    ShippingTrackDO getByLogisticNoAndLogisticsStatus(@Param("logisticNo") String logisticNo,
                                                      @Param("logisticsStatus") Integer logisticsStatus);
}
