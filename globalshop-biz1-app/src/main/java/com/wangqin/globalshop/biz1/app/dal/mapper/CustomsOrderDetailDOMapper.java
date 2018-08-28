package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CustomsOrderDetailDO;

public interface CustomsOrderDetailDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomsOrderDetailDO record);

    int insertSelective(CustomsOrderDetailDO record);

    CustomsOrderDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomsOrderDetailDO record);

    int updateByPrimaryKey(CustomsOrderDetailDO record);
}