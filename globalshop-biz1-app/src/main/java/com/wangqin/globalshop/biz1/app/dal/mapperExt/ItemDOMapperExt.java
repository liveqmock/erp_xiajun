package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemDOMapper;
import com.wangqin.globalshop.biz1.app.dto.ItemDTO;

import java.util.List;
import java.util.Map;



/**
 * Create by 777 on 2018/5/25
 */
public interface ItemDOMapperExt extends ItemDOMapper {

	//插入单个商品
	Long insertItemSelective(ItemDO item);
	
	public List<ItemDO> selectBatchIds(List<Long> idList);

	public void updateBatchById(List<ItemDO> itemDOList);

    Integer queryItemsCount(ItemQueryVO itemQueryVO);
	
	List<ItemDTO> queryItems(ItemQueryVO itemQueryVO);
	
	ItemDTO queryItemById(Long id);
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

    ItemDO selectByItemCode(String itemCode);
	
    //根据id更新商品
	void updateByIdSelective(ItemDO item);
	
	Long queryIdByItemCode(String itemCode);
	//一键分享首页商品
	List<ItemDO> queryItemByStatus(String companyNo, String status, int start, String pageSize);
	
	//一键分享搜索商品
	List<ItemDO> queryItemByKeyWord(String keyWord, String companyNo, int start, String pageSize);
	
	//一键分享商品详情
	ItemDO itemDetailByItemCode(String itemCode, String companyNo);
	
	//一键分享，获取商品的图片
	String queryItemPicByItemCode(String itemCode);
}
