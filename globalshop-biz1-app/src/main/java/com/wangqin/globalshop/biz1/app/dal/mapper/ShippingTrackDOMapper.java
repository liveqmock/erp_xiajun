package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShippingTrackDOMapper {
    int countByExample(ShippingTrackDOExample example);

    int deleteByExample(ShippingTrackDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShippingTrackDO record);

    int insertSelective(ShippingTrackDO record);

    List<ShippingTrackDO> selectByExample(ShippingTrackDOExample example);

    ShippingTrackDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShippingTrackDO record, @Param("example") ShippingTrackDOExample example);

    int updateByExample(@Param("record") ShippingTrackDO record, @Param("example") ShippingTrackDOExample example);

    int updateByPrimaryKeySelective(ShippingTrackDO record);

    int updateByPrimaryKey(ShippingTrackDO record);
}