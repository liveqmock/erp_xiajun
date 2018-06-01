package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.common.enums.InventoryRecord;

import java.util.List;

public interface InventoryBookingRecordService {

	List<InventoryBookingRecordDO> queryByErpOrderId(String orderNo);

    void delete(InventoryBookingRecordDO inventoryRecord);

    void insert(InventoryBookingRecordDO inventoryRecord);

    List<InventoryRecord> sumBookedByInventoryType(String orderNo);

    void updates(List<InventoryBookingRecordDO> records);

    List<InventoryOnWareHouseDO> sumInventoryBySkuIdGroupbyWarehouse(String itemCode, String skuCode, Object o);

    InventoryBookingRecordDO queryById(Long id);
}
