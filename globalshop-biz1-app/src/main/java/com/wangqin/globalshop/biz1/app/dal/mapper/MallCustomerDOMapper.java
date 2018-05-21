package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCustomerDO;

public interface MallCustomerDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallCustomerDO record);

    int insertSelective(MallCustomerDO record);

    MallCustomerDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallCustomerDO record);

    int updateByPrimaryKey(MallCustomerDO record);
}