package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;

public interface ItemCategoryDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemCategoryDO record);

    int insertSelective(ItemCategoryDO record);

    ItemCategoryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemCategoryDO record);

    int updateByPrimaryKey(ItemCategoryDO record);
}