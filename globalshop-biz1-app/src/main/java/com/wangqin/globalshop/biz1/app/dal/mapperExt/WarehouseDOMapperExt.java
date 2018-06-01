package com.wangqin.globalshop.biz1.app.dal.mapperExt;

<<<<<<< HEAD
import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WareHouseDOMapper;

public interface WarehouseDOMapperExt extends WareHouseDOMapper {
=======
import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WarehouseMapper;

import java.util.List;

public interface WarehouseDOMapperExt extends WarehouseMapper {
>>>>>>> b4e254f62c8db97eb4584281c6523408e497ea44
    List<WarehouseDO> queryPoList(WarehouseDO so);
}