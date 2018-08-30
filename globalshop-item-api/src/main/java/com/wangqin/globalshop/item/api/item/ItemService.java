package com.wangqin.globalshop.item.api.item;




import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;


/**
 * 
 */


public interface ItemService {

	@RequestMapping(value = "/item/queryItemDOByItemCode", method = RequestMethod.POST)
	ItemDO queryItemDOByItemCode(@RequestParam("itemCode") String itemCode);

	@RequestMapping(value = "/item/queryItemCodeById", method = RequestMethod.POST)
	String queryItemCodeById(@RequestParam("id") Long id);
	//根据id更新商品
	@RequestMapping(value = "/item/updateByIdSelective", method = RequestMethod.POST)
	void updateByIdSelective(@RequestBody ItemDO item);
	
	//插入单个商品
	@RequestMapping(value = "/item/insertItemSelective", method = RequestMethod.POST)
	Long insertItemSelective(@RequestBody ItemDO item);
		



	@RequestMapping(value = "/item/generateItemShareUrl", method = RequestMethod.POST)
	String generateItemShareUrl(@RequestParam("userId") String userId, @RequestParam("companyNo") String companyNo,
								@RequestParam("itemCode") String itemCode, @RequestParam("pages") String pages,
								@RequestParam("accessToken") String accessToken);

//	@RequestMapping(value = "/item/getTokenFromCache", method = RequestMethod.POST)
//	public ShareTokenEntity getTokenFromCache(@RequestParam("uuid") String uuid);
	
	/**
	 * 按照条件分页查询商品
	 * @param itemQueryVO
	 * @return
	 */
	@RequestMapping(value = "/item/queryItems", method = RequestMethod.POST)
	JsonPageResult<List<ItemDTO>> queryItems(@RequestBody ItemQueryVO itemQueryVO);

	@RequestMapping(value = "/item/queryItemById", method = RequestMethod.POST)
    ItemDTO queryItemById(@RequestParam("id") Long id);
	

	/**
	 * 2017-04-04,jc
	 * 返回所有的 Id and ItemCode
	 * @return
	 */
	@RequestMapping(value = "/item/queryAllItemCodeAndIdHashMap", method = RequestMethod.POST)
	Map<String, Long> queryAllItemCodeAndIdHashMap();

	@RequestMapping(value = "/item/insertIntoItemDimension", method = RequestMethod.POST)
	String insertIntoItemDimension(@RequestParam("sceneStr") String sceneStr, @RequestParam("pages") String pages,
								   @RequestParam("accessToken") String accessToken);
	
	/**
	 * 分页查询海狐商品
	 * @param itemQueryVO
	 * @return
	 */
	@RequestMapping(value = "/item/queryHaihuItems", method = RequestMethod.POST)
	JsonPageResult<List<ItemDO>> queryHaihuItems(@RequestBody ItemQueryVO itemQueryVO);
	
	/**
	 * 根据跟新时间提取海狐商品
	 * @param gmtModify
	 * @return
	 */
	@RequestMapping(value = "/item/queryHaihuByUptime", method = RequestMethod.POST)
	List<ItemDO> queryHaihuByUptime(@RequestBody ItemQueryVO itemQueryVO);

	@RequestMapping(value = "/item/sumNewItemNumByDate", method = RequestMethod.POST)
	Integer sumNewItemNumByDate(@RequestParam("days") Integer days);

	@RequestMapping(value = "/item/sumNewItemNumByMonth", method = RequestMethod.POST)
	Integer sumNewItemNumByMonth(@RequestParam("months") Integer months);

	@RequestMapping(value = "/item/queryItemListSelective", method = RequestMethod.POST)
	List<ItemDTO> queryItemListSelective(@RequestBody ItemQueryVO itemQueryVO);

	@RequestMapping(value = "/item/queryIdByItemCode", method = RequestMethod.POST)
	Long queryIdByItemCode(@RequestParam("itemCode") String itemCode);

//    //渠道模块使用
//	public ItemVo queryAdd(String itemCode);
//
//	//渠道模块使用
//	public GlobalShopItemVo queryUpdate(String itemCode, String shopCode);
//
//	public void dealItemAndChannelItem4JdAdd(JdCommonParam jdCommonParam, GlobalShopItemVo globalShopItemVo);
//
//	public void dealItemAndChannelItem4JdTask(JdCommonParam jdCommonParam, GlobalShopItemVo globalShopItemVo);

	//一键分享的首页商品列表
	@RequestMapping(value = "/item/queryItemByStatus", method = RequestMethod.POST)
	List<ItemDO> queryItemByStatus(@RequestParam("companyNo") String companyNo, @RequestParam("status") String status,
								   @RequestParam("start") int start, @RequestParam("pageSize") int pageSize);

	//一键分享搜索商品
	@RequestMapping(value = "/item/queryItemByKeyWord", method = RequestMethod.POST)
	List<ItemDO> queryItemByKeyWord(@RequestBody List<String> keyWord, @RequestParam("companyNo") String companyNo,
									@RequestParam("start") int start, @RequestParam("pageSize") int pageSize);

	//一键分享商品详情
	@RequestMapping(value = "/item/itemDetailByItemCode", method = RequestMethod.POST)
	ItemDO itemDetailByItemCode(@RequestParam("itemCode") String itemCode, @RequestParam("companyNo") String companyNo);

	//一键分享，获取商品的图片
	@RequestMapping(value = "/item/queryItemPicByItemCode", method = RequestMethod.POST)
	String queryItemPicByItemCode(@RequestParam("itemCode") String itemCode);

	//一键分享，获取商品的图片
	@RequestMapping(value = "/item/queryItemPicByItemCodeAndCompanyNo", method = RequestMethod.POST)
	String queryItemPicByItemCodeAndCompanyNo(@RequestParam("itemCode") String itemCode, @RequestParam("companyNo") String companyNo);




	@RequestMapping(value = "/item/generateQrCode", method = RequestMethod.POST)
	void generateQrCode(@RequestBody ItemDO newItem, @RequestParam("companyNo") String companyNo);

}
