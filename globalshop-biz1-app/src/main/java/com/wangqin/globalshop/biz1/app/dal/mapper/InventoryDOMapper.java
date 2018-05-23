package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;

public interface InventoryDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryDO record);

    int insertSelective(InventoryDO record);

    InventoryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryDO record);

    int updateByPrimaryKey(InventoryDO record);
}