package com.wangqin.globalshop.item.app.service;


import java.util.List;
import java.util.Map;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;




/**
 * 
 */


public interface IItemService {

	
	
	ItemDO selectByPrimaryKey(Long id);
	/**
	 * add item 
	 * @param item
	 * @param itemSkuList
	 */
	void addItem(ItemDO item);
	
	
	/**
	 * update item 
	 * @param item
	 * @param itemSkuList
	 */
	void updateItem(ItemDO item);
	
	/**
	 * query item 
	 * @param item
	 * @param itemSkuList
	 */
	ItemDO queryItem(Long id);
	
	/**
	 * 按照条件分页查询商品
	 * @param itemQueryVO
	 * @return
	 */
	JsonPageResult<List<ItemDTO>> queryItems(ItemQueryVO itemQueryVO);
	
    ItemDTO queryItemById(Long id);
	
	/**
	 * 通过ID查询多个商品
	 * @param ids
	 * @return
	 */
	List<ItemDO> queryItems(List<Long> ids);

	/**
	 * 2017-04-04,jc
	 * 返回所有的 Id and ItemCode
	 * @return
	 */
	Map<String, Long> queryAllItemCodeAndIdHashMap();
	
	String insertIntoItemDimension(String sceneStr, String pages, String accessToken);
	
	/**
	 * 分页查询海狐商品
	 * @param itemQueryVO
	 * @return
	 */
	JsonPageResult<List<ItemDO>> queryHaihuItems(ItemQueryVO itemQueryVO);
	
	/**
	 * 根据跟新时间提取海狐商品
	 * @param gmtModify
	 * @return
	 */
	List<ItemDO> queryHaihuByUptime(ItemQueryVO itemQueryVO);

	Integer sumNewItemNumByDate(Integer days);
	
	Integer sumNewItemNumByMonth(Integer months);
	
	List<ItemDTO> queryItemListSelective(ItemQueryVO itemQueryVO);
	
	void updateItemById(ItemQueryVO itemQueryVO);
}
