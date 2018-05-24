package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountConfigDO;

public interface ChannelAccountConfigDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelAccountConfigDO record);

    int insertSelective(ChannelAccountConfigDO record);

    ChannelAccountConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelAccountConfigDO record);

    int updateByPrimaryKey(ChannelAccountConfigDO record);
}