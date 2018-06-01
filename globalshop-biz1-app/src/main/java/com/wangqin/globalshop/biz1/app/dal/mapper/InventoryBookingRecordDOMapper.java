package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;

public interface InventoryBookingRecordDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryBookingRecordDO record);

    int insertSelective(InventoryBookingRecordDO record);

    InventoryBookingRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryBookingRecordDO record);

    int updateByPrimaryKey(InventoryBookingRecordDO record);

}