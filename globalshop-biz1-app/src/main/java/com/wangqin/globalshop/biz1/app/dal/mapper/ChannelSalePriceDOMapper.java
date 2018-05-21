package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSalePriceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelSalePriceDOMapper {
    int countByExample(ChannelSalePriceDOExample example);

    int deleteByExample(ChannelSalePriceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChannelSalePriceDO record);

    int insertSelective(ChannelSalePriceDO record);

    List<ChannelSalePriceDO> selectByExample(ChannelSalePriceDOExample example);

    ChannelSalePriceDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChannelSalePriceDO record, @Param("example") ChannelSalePriceDOExample example);

    int updateByExample(@Param("record") ChannelSalePriceDO record, @Param("example") ChannelSalePriceDOExample example);

    int updateByPrimaryKeySelective(ChannelSalePriceDO record);

    int updateByPrimaryKey(ChannelSalePriceDO record);
}