package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryScaleDO;

public interface ItemCategoryScaleDOMapper {
    int insert(ItemCategoryScaleDO record);

    int insertSelective(ItemCategoryScaleDO record);
}