package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;

public interface InventoryDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryDO record);



    InventoryDO selectByPrimaryKey(Long id);



    int updateByPrimaryKey(InventoryDO record);
    /*************************new *******************/
    int insertSelective(InventoryDO record);
    int updateByPrimaryKeySelective(InventoryDO record);
}