package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallShippingCartDO;

public interface MallShippingCartDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallShippingCartDO record);

    int insertSelective(MallShippingCartDO record);

    MallShippingCartDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallShippingCartDO record);

    int updateByPrimaryKey(MallShippingCartDO record);
}