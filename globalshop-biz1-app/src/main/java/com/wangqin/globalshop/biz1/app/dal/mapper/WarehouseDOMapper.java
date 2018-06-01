package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;

public interface WarehouseDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WarehouseDO record);

    int insertSelective(WarehouseDO record);

    WarehouseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WarehouseDO record);

    int updateByPrimaryKey(WarehouseDO record);

    WarehouseDO selectByWarehourseNo(String warehouseNo);
}