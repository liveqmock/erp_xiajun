package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WarehouseDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarehouseDOMapperExt extends WarehouseDOMapper {

    List<WarehouseDO> selectWhList(WarehouseDO so);
    WarehouseDO selectByWarehouseNo(@Param("warehouseNo") String warehouseNo);

    List<WarehouseDO> queryPoList(WarehouseDO so);

    List<WarehouseDO> selectByCompanyNo(String companyNo);
}
