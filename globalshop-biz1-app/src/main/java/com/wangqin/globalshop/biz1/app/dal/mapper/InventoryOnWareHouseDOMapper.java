package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;

public interface InventoryOnWareHouseDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryOnWareHouseDO record);

    int insertSelective(InventoryOnWareHouseDO record);

    InventoryOnWareHouseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryOnWareHouseDO record);

    int updateByPrimaryKey(InventoryOnWareHouseDO record);
}