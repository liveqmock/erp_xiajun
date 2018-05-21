package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemFindDO;

public interface ItemFindDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemFindDO record);

    int insertSelective(ItemFindDO record);

    ItemFindDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemFindDO record);

    int updateByPrimaryKeyWithBLOBs(ItemFindDO record);

    int updateByPrimaryKey(ItemFindDO record);
}