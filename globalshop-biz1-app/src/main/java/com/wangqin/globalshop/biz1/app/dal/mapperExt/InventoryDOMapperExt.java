package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryDOMapper;

public interface InventoryDOMapperExt extends InventoryDOMapper {
    InventoryDO queryInventoryByCode(String itemCode, String skuCode);

    int updateLockInventory(Long id, Long booked, Long transBooked);
}