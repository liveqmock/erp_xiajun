package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryBookingRecordDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryBookingRecordDOMapperExt extends InventoryBookingRecordDOMapper {

    List<InventoryBookingRecordDO> selectByOrderNo(@Param("orderNo") String orderNo);

    List<InventoryBookingRecordDO> selectBySubOrderNo(@Param("subOrderNo") String subOrderNo);

    List<InventoryBookingRecordDO> sumBookedByInventoryType(String orderNo);

    List<InventoryBookingRecordDO> queryByOnWarehouseNoAndInventoryType(String inventoryOnWarehouseNo, String inventoryType);
}
