package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;

public interface ItemSkuScaleDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemSkuScaleDO record);

    int insertSelective(ItemSkuScaleDO record);

    ItemSkuScaleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemSkuScaleDO record);

    int updateByPrimaryKey(ItemSkuScaleDO record);
}