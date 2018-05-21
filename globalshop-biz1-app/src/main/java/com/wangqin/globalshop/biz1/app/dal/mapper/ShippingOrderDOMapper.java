package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;

public interface ShippingOrderDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShippingOrderDO record);

    int insertSelective(ShippingOrderDO record);

    ShippingOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShippingOrderDO record);

    int updateByPrimaryKey(ShippingOrderDO record);
}