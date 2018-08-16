package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShengpayRefundDO;

public interface ShengpayRefundDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShengpayRefundDO record);

    int insertSelective(ShengpayRefundDO record);

    ShengpayRefundDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShengpayRefundDO record);

    int updateByPrimaryKey(ShengpayRefundDO record);
}