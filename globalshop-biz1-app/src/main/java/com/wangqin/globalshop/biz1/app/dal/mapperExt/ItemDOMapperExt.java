package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/5/25
 */
public interface ItemDOMapperExt extends ItemDOMapper {

	public List<ItemDO> selectBatchIds(List<Long> idList);

	public void updateBatchById(List<ItemDO> itemDOList);

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
}
