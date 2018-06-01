package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface WarehouseDOMapper {
    WarehouseDO selectByWarehourseNo(String warehouseNo);
}
