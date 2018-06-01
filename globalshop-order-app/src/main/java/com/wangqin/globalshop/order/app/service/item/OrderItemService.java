package com.wangqin.globalshop.order.app.service.item;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;


/**
 * 
 */


public interface OrderItemService {

	ItemDO queryItemByItemCode(String itemCode);
	

}
