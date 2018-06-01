package com.wangqin.globalshop.channel.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryDOMapper;

/**
 * Create by 777 on 2018/5/29
 */
public interface CAInventoryDOMapperExt extends InventoryDOMapper {

	public InventoryDO queryInventoryByCode(String itemCode, String skuCode);


	int updateLockInventory(Long id, Long booked, Long transBooked);
}
