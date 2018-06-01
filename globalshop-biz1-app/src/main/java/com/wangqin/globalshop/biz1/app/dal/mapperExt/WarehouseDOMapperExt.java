package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WarehouseMapper;

public interface WarehouseDOMapperExt extends WarehouseMapper {

    List<WarehouseDO> queryPoList(WarehouseDO so);
}
