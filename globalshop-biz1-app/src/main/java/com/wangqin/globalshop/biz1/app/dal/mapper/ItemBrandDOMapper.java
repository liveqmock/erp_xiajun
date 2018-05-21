package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;

public interface ItemBrandDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemBrandDO record);

    int insertSelective(ItemBrandDO record);

    ItemBrandDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemBrandDO record);

    int updateByPrimaryKey(ItemBrandDO record);
}