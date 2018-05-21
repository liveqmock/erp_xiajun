package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;

public interface ItemSkuDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemSkuDO record);

    int insertSelective(ItemSkuDO record);

    ItemSkuDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemSkuDO record);

    int updateByPrimaryKeyWithBLOBs(ItemSkuDO record);

    int updateByPrimaryKey(ItemSkuDO record);
}