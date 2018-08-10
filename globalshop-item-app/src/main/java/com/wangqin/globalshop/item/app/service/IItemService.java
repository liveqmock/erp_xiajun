package com.wangqin.globalshop.item.app.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQuery2VO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.channelapi.dal.GlobalShopItemVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.item.app.service.impl.entity.ShareTokenEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 */


public interface IItemService {


	ItemDO queryItemDOByItemCode(String itemCode);
	
	String queryItemCodeById(Long id);
	//根据id更新商品
	void updateByIdSelective(ItemDO item);
	
	//插入单个商品
	Long insertItemSelective(ItemDO item);
		

	/**
	 * 添加商品
	 * @param item
	 * @param itemSkuList
	 */
	Object addItem(ItemQueryVO item);

	String generateItemShareUrl(String userId, String companyNo, String itemCode, String pages, String accessToken);

	public ShareTokenEntity getTokenFromCache(String uuid);

	
	
	
	/**
	 * query item 
	 * @param item
	 * @param itemSkuList
	 */
	ItemDO queryIte(Long id);
	
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
	
	
	Long queryIdByItemCode(String itemCode);

    //渠道模块使用
	public ItemVo queryAdd(String itemCode);

	//渠道模块使用
	public GlobalShopItemVo queryUpdate(String itemCode, String shopCode);

	public void dealItemAndChannelItem4JdAdd(JdCommonParam jdCommonParam, GlobalShopItemVo globalShopItemVo);

	public void dealItemAndChannelItem4JdTask(JdCommonParam jdCommonParam, GlobalShopItemVo globalShopItemVo);

	//一键分享的首页商品列表
	List<ItemDO> queryItemByStatus(String companyNo, String status, int start, int pageSize);

	//一键分享搜索商品
	List<ItemDO> queryItemByKeyWord(List<String> keyWord, String companyNo, int start, int pageSize);

	//一键分享商品详情
	ItemDO itemDetailByItemCode(String itemCode, String companyNo);

	//一键分享，获取商品的图片
	String queryItemPicByItemCode(String itemCode);

	//一键分享，获取商品的图片
	String queryItemPicByItemCodeAndCompanyNo(String itemCode, String companyNo);


	/**
	 * 导入商品列表
	 * @param list
	 */
    void importItem(List<List<Object>> list) throws Exception;

	/**
	 * 根据指定条件分页查询商品
	 *
	 * @param itemQueryVO
	 * @param pageQueryParam
	 * @return
	 */
    List<ItemDTO> listItems(ItemQuery2VO itemQueryVO, PageQueryParam pageQueryParam);

	/**
	 * 根据指定条件查询商品数目
	 *
	 * @param itemQueryVO
	 * @return
	 */
	int countItems(ItemQuery2VO itemQueryVO);
}
