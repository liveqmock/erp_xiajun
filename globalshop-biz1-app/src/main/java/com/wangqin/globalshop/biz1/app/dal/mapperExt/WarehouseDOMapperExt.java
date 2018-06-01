package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WarehouseMapper;

import java.util.List;

public interface WarehouseDOMapperExt extends WarehouseMapper {

    List<WarehouseDO> selectWhList(WarehouseDO so);
    WarehouseDO selectByWarehourseNo(String warehouseNo);

    List<WarehouseDO> queryPoList(WarehouseDO so);

    List<WarehouseDO> selectByCompanyNo(String companyNo);
}
