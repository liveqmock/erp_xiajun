package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDetailDO;

public interface BuyerTaskDetailDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerTaskDetailDO record);

    int insertSelective(BuyerTaskDetailDO record);

    BuyerTaskDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerTaskDetailDO record);

    int updateByPrimaryKey(BuyerTaskDetailDO record);
}