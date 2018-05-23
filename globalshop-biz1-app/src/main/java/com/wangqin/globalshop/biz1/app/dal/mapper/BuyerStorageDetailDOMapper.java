package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDetailDO;

public interface BuyerStorageDetailDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerStorageDetailDO record);

    int insertSelective(BuyerStorageDetailDO record);

    BuyerStorageDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerStorageDetailDO record);

    int updateByPrimaryKey(BuyerStorageDetailDO record);
}