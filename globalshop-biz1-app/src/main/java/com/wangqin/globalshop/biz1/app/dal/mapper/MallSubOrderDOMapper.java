package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

public interface MallSubOrderDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSubOrderDO record);

    int insertSelective(MallSubOrderDO record);

    MallSubOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSubOrderDO record);

    int updateByPrimaryKey(MallSubOrderDO record);
}