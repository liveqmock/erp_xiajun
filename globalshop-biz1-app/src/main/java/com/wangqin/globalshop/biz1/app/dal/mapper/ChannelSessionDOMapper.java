package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelSessionDO;

public interface ChannelSessionDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelSessionDO record);

    int insertSelective(ChannelSessionDO record);

    ChannelSessionDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelSessionDO record);

    int updateByPrimaryKey(ChannelSessionDO record);
}