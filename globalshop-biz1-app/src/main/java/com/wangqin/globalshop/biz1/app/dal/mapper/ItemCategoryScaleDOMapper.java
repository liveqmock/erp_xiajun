package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryScaleDO;

public interface ItemCategoryScaleDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemCategoryScaleDO record);

    int insertSelective(ItemCategoryScaleDO record);

    ItemCategoryScaleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemCategoryScaleDO record);

    int updateByPrimaryKey(ItemCategoryScaleDO record);
}