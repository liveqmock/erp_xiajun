package com.wangqin.globalshop.channel.service.inventory;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryBookingRecordDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 777 on 2018/6/2
 */
@Service
public class InventoryBookingRecordChannelServiceImpl implements IInventoryBookingRecordChannelService{

	@Autowired
	InventoryBookingRecordDOMapperExt inventoryBookingRecordDOMapperExt;


	public void updateBatchInvRecord(List<InventoryBookingRecordDO> inventoryBookingRecordDOS){
		for(InventoryBookingRecordDO record : inventoryBookingRecordDOS){
			inventoryBookingRecordDOMapperExt.updateByPrimaryKey(record);
		}

	}



}
