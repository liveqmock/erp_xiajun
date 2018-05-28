package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;
import java.util.Map;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;


public interface ItemDOMapperExt extends ItemDOMapper{

    Integer queryItemsCount(ItemQueryVO itemQueryVO);
	
	List<ItemDO> queryItems(ItemQueryVO itemQueryVO);
	
	/**
	 * 2017-04-04, jc
	 * query all itemCode and id
	 * 
	 * @return
	 */
	List<Map<String, Object>> queryAllItemCodeAndIdHashMap();
	
	void updateItemNotSale();
	
	void updateItemSale();
	
	Integer queryItemsCountByhaihu(ItemQueryVO itemQueryVO);
	
	List<ItemDO> queryHaihuItems(ItemQueryVO itemQueryVO);
	
	List<ItemDO> queryHaihuByUptime(ItemQueryVO itemQueryVO);
	
	Integer sumNewItemNumByDate(Integer days);
	
	Integer sumNewItemNumByMonth(Integer months);
	
	ItemDO queryItemByItemCode(String itemCode);
}
