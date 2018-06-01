package com.wangqin.globalshop.order.app.service.inventory;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.common.enums.InventoryRecord;

import java.util.List;

public interface OrderInventoryBookingRecordService {

	List<InventoryBookingRecordDO> queryByErpOrderId(String orderNo);

    void delete(InventoryBookingRecordDO inventoryRecord);

    void insert(InventoryBookingRecordDO inventoryRecord);

    List<InventoryRecord> sumBookedByInventoryType(String orderNo);

    void updates(List<InventoryBookingRecordDO> records);
}
