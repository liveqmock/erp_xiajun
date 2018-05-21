package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;

public interface ItemSkuScaleDOMapper {
    int insert(ItemSkuScaleDO record);

    int insertSelective(ItemSkuScaleDO record);
}