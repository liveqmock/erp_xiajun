package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WarehouseMapper;

import java.util.List;

public interface WarehouseDOMapperExt extends WarehouseMapper {

    List<WarehouseDO> queryPoList(WarehouseDO so);
    WarehouseDO selectByWarehourseNo(String warehouseNo);
}
