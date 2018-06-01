package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryBookingRecordDOMapper;

import java.util.List;

public interface InventoryBookingRecordDOMapperExt extends InventoryBookingRecordDOMapper {
    List<InventoryBookingRecordDO> selectByOrderNo(String orderNo);
}