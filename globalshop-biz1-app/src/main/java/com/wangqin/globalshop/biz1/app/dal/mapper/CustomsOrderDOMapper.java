package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CustomsOrderDO;

public interface CustomsOrderDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomsOrderDO record);

    int insertSelective(CustomsOrderDO record);

    CustomsOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomsOrderDO record);

    int updateByPrimaryKey(CustomsOrderDO record);
}