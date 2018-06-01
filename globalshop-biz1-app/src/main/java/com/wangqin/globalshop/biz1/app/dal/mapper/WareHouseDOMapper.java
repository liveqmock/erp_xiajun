package com.wangqin.globalshop.biz1.app.dal.mapper;


import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;

public interface WareHouseDOMapper {
    WarehouseDO selectByWarehourseNo(String warehouseNo);
}