package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;

import java.util.List;

public interface InventoryBookingRecordService {

	List<InventoryBookingRecordDO> queryByErpOrderId(String orderNo);

    void delete(InventoryBookingRecordDO inventoryRecord);

    void insert(InventoryBookingRecordDO inventoryRecord);

    List<InventoryBookingRecordDO> sumBookedByInventoryType(String orderNo);


    List<InventoryOnWareHouseDO> sumInventoryBySkuIdGroupbyWarehouse(String itemCode, String skuCode, Object o);

    InventoryBookingRecordDO queryById(Long id);

    List<InventoryBookingRecordDO> queryByOnWarehouseNoAndInventoryType(String inventoryOnWarehouseNo, String transInv);

    void updateSelectiveById(InventoryBookingRecordDO inventoryRecord);

    List<InventoryBookingRecordDO> sumInventoryCheckRecord(Long id, String transInv, List<Integer> integers);

    List<InventoryOnWareHouseDO> selectByCompanyNoAndSkuCode(String companyNo, String skuCode);

    List<InventoryOnWareHouseDO> selectByCompanyNo(String s);
}
