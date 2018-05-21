package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;

public interface BuyerTaskDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerTaskDO record);

    int insertSelective(BuyerTaskDO record);

    BuyerTaskDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerTaskDO record);

    int updateByPrimaryKey(BuyerTaskDO record);
}