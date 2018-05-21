package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ShippingTrackPollingYuntongDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ShippingTrackPollingYuntongDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShippingTrackPollingYuntongDOMapper {
    int countByExample(ShippingTrackPollingYuntongDOExample example);

    int deleteByExample(ShippingTrackPollingYuntongDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShippingTrackPollingYuntongDO record);

    int insertSelective(ShippingTrackPollingYuntongDO record);

    List<ShippingTrackPollingYuntongDO> selectByExample(ShippingTrackPollingYuntongDOExample example);

    ShippingTrackPollingYuntongDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShippingTrackPollingYuntongDO record, @Param("example") ShippingTrackPollingYuntongDOExample example);

    int updateByExample(@Param("record") ShippingTrackPollingYuntongDO record, @Param("example") ShippingTrackPollingYuntongDOExample example);

    int updateByPrimaryKeySelective(ShippingTrackPollingYuntongDO record);

    int updateByPrimaryKey(ShippingTrackPollingYuntongDO record);
}