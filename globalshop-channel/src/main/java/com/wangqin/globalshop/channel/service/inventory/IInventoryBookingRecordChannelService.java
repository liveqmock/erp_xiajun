package com.wangqin.globalshop.channel.service.inventory;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/2
 */
public interface IInventoryBookingRecordChannelService {

	public void updateBatchInvRecord(List<InventoryBookingRecordDO> inventoryBookingRecordDOS);

}
