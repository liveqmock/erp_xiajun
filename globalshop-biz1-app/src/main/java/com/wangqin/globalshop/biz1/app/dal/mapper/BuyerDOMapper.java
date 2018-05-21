package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;

public interface BuyerDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerDO record);

    int insertSelective(BuyerDO record);

    BuyerDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerDO record);

    int updateByPrimaryKey(BuyerDO record);
}