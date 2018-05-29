package com.wangqin.globalshop.order.app.service.inventory;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;

import java.util.List;

public interface OrderInventoryBookingRecordService {

	List<InventoryBookingRecordDO> queryByErpOrderId(String orderNo);
}
