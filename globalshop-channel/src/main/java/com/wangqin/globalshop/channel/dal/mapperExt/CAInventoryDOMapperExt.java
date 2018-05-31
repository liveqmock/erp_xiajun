package com.wangqin.globalshop.channel.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryDOMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Create by 777 on 2018/5/29
 */
public interface CAInventoryDOMapperExt extends InventoryDOMapper {

	public InventoryDO queryInventoryByCode(@Param("itemCode") String itemCode, @Param("skuCode") String skuCode);


	int updateLockInventory(Long id, Long booked, Long transBooked);
}
