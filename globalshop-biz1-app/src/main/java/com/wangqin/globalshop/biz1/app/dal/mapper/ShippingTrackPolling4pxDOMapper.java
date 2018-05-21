package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackPolling4pxDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackPolling4pxDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShippingTrackPolling4pxDOMapper {
    int countByExample(ShippingTrackPolling4pxDOExample example);

    int deleteByExample(ShippingTrackPolling4pxDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShippingTrackPolling4pxDO record);

    int insertSelective(ShippingTrackPolling4pxDO record);

    List<ShippingTrackPolling4pxDO> selectByExample(ShippingTrackPolling4pxDOExample example);

    ShippingTrackPolling4pxDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShippingTrackPolling4pxDO record, @Param("example") ShippingTrackPolling4pxDOExample example);

    int updateByExample(@Param("record") ShippingTrackPolling4pxDO record, @Param("example") ShippingTrackPolling4pxDOExample example);

    int updateByPrimaryKeySelective(ShippingTrackPolling4pxDO record);

    int updateByPrimaryKey(ShippingTrackPolling4pxDO record);
}