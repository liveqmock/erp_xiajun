package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryInoutDO;

public interface InventoryInoutDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryInoutDO record);

    int insertSelective(InventoryInoutDO record);

    InventoryInoutDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryInoutDO record);

    int updateByPrimaryKey(InventoryInoutDO record);
}