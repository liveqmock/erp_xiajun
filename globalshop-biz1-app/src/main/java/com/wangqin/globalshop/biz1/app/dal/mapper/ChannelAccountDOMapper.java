package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;

public interface ChannelAccountDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelAccountDO record);

    int insertSelective(ChannelAccountDO record);

    ChannelAccountDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelAccountDO record);

    int updateByPrimaryKey(ChannelAccountDO record);
}