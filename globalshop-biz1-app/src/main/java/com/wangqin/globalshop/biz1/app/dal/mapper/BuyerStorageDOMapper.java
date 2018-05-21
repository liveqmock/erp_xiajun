package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;

public interface BuyerStorageDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerStorageDO record);

    int insertSelective(BuyerStorageDO record);

    BuyerStorageDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerStorageDO record);

    int updateByPrimaryKey(BuyerStorageDO record);
}