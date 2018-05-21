package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallShippingAddressDO;

public interface MallShippingAddressDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallShippingAddressDO record);

    int insertSelective(MallShippingAddressDO record);

    MallShippingAddressDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallShippingAddressDO record);

    int updateByPrimaryKey(MallShippingAddressDO record);
}