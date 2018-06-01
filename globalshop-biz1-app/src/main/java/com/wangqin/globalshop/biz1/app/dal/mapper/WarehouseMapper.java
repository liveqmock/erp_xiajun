package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface WarehouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WarehouseDO record);

    int insertSelective(WarehouseDO record);

    WarehouseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WarehouseDO record);

    int updateByPrimaryKey(WarehouseDO record);
}
