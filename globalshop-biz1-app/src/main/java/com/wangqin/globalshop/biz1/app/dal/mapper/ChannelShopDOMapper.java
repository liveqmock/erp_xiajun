package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;

public interface ChannelShopDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelShopDO record);

    int insertSelective(ChannelShopDO record);

    ChannelShopDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelShopDO record);

    int updateByPrimaryKey(ChannelShopDO record);
}