package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WareHouseDOMapper;

public interface WarehouseDOMapperExt extends WareHouseDOMapper {
    List<WarehouseDO> queryPoList(WarehouseDO so);
}