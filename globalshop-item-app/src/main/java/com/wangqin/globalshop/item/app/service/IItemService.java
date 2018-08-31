package com.wangqin.globalshop.item.app.service;

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

	String generateItemShareUrl(String userId, String companyNo, String itemCode, String pages, String accessToken);

	public ShareTokenEntity getTokenFromCache(String uuid);
	
	/**
	 * 按照条件分页查询商品
	 * @param itemQueryVO
	 * @return
	 */
	JsonPageResult<List<ItemDTO>> queryItems(ItemQueryVO itemQueryVO);
	
    ItemDTO queryItemById(Long id);		
	
	String insertIntoItemDimension(String sceneStr, String pages, String accessToken);
		
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
    
    void generateQrCode(ItemDO newItem, String companyNo);


}
