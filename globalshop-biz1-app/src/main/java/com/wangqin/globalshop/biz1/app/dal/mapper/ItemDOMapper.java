package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;

public interface ItemDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKeyWithBLOBs(ItemDO record);

    int updateByPrimaryKey(ItemDO record);
}