package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShengpayPayDO;

public interface ShengpayPayDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShengpayPayDO record);

    int insertSelective(ShengpayPayDO record);

    ShengpayPayDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShengpayPayDO record);

    int updateByPrimaryKey(ShengpayPayDO record);
}