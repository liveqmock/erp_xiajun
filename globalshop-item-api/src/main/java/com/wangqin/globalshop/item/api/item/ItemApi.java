package com.wangqin.globalshop.item.api.item;

import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.channelapi.dal.GlobalShopItemVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.item.dto.ShareTokenEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Create by 777 on 2018/8/26
 */
public interface ItemApi {

	//第一部分：首先来自于com.wangqin.globalshop.item.app.service.IItemService

	@RequestMapping(value = "/queryItemDOByItemCode", method = RequestMethod.POST)
	ItemDO queryItemDOByItemCode(String itemCode);

	@RequestMapping(value = "/queryItemCodeById", method = RequestMethod.POST)
	String queryItemCodeById(Long id);


	//根据id更新商品
	@RequestMapping(value = "/updateByIdSelective", method = RequestMethod.POST)
	void updateByIdSelective(ItemDO item);

	//插入单个商品
	@RequestMapping(value = "/insertItemSelective", method = RequestMethod.POST)
	Long insertItemSelective(ItemDO item);


	/**
	 * 添加商品
	 * @param item
	 * @param
	 */
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	Object addItem(@RequestBody ItemQueryVO item);

	@RequestMapping(value = "/generateItemShareUrl", method = RequestMethod.POST)
	String generateItemShareUrl(
			@RequestParam("userId") String userId,
			@RequestParam("companyNo") String companyNo,
			@RequestParam("itemCode") String itemCode,
			@RequestParam("pages") String pages,
			@RequestParam("accessToken") String accessToken);

	@RequestMapping(value = "/getTokenFromCache", method = RequestMethod.POST)
	public ShareTokenEntity getTokenFromCache(@RequestParam("uuid") String uuid);

	/**
	 * 按照条件分页查询商品
	 * @param itemQueryVO
	 * @return
	 */
	@RequestMapping(value = "/queryItems", method = RequestMethod.POST)
	JsonPageResult<List<ItemDTO>> queryItems(@RequestBody ItemQueryVO itemQueryVO);

	@RequestMapping(value = "/queryItemById", method = RequestMethod.POST)
	ItemDTO queryItemById(@RequestParam("id") Long id);


	/**
	 * 2017-04-04,jc
	 * 返回所有的 Id and ItemCode
	 * @return
	 */

	@RequestMapping(value = "/queryAllItemCodeAndIdHashMap", method = RequestMethod.POST)
	Map<String, Long> queryAllItemCodeAndIdHashMap();

	@RequestMapping(value = "/insertIntoItemDimension", method = RequestMethod.POST)
	String insertIntoItemDimension(
			@RequestParam("sceneStr") String sceneStr,
			@RequestParam("pages") String pages,
			@RequestParam("accessToken") String accessToken);

	/**
	 * 分页查询海狐商品
	 * @param itemQueryVO
	 * @return
	 */
	@RequestMapping(value = "/queryHaihuItems", method = RequestMethod.POST)
	JsonPageResult<List<ItemDO>> queryHaihuItems(@RequestBody ItemQueryVO itemQueryVO);

	/**
	 * 根据跟新时间提取海狐商品
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryHaihuByUptime", method = RequestMethod.POST)
	List<ItemDO> queryHaihuByUptime(@RequestBody ItemQueryVO itemQueryVO);

	@RequestMapping(value = "/sumNewItemNumByDate", method = RequestMethod.POST)
	Integer sumNewItemNumByDate(@RequestParam("days") Integer days);

	@RequestMapping(value = "/sumNewItemNumByMonth", method = RequestMethod.POST)
	Integer sumNewItemNumByMonth(@RequestParam("months") Integer months);

	@RequestMapping(value = "/queryItemListSelective", method = RequestMethod.POST)
	List<ItemDTO> queryItemListSelective(@RequestBody ItemQueryVO itemQueryVO);


	@RequestMapping(value = "/queryIdByItemCode", method = RequestMethod.POST)
	Long queryIdByItemCode(@RequestParam("itemCode") String itemCode);

	//渠道模块使用
	@RequestMapping(value = "/queryAdd", method = RequestMethod.POST)
	public ItemVo queryAdd(@RequestParam("itemCode") String itemCode);

	//渠道模块使用
	@RequestMapping(value = "/queryUpdate", method = RequestMethod.POST)
	public GlobalShopItemVo queryUpdate(
			@RequestParam("itemCode") String itemCode,
			@RequestParam("shopCode") String shopCode);

	@RequestMapping(value = "/dealItemAndChannelItem4JdAdd", method = RequestMethod.POST)
	public void dealItemAndChannelItem4JdAdd(@RequestBody JdCommonParam jdCommonParam, @RequestBody GlobalShopItemVo globalShopItemVo);

	@RequestMapping(value = "/dealItemAndChannelItem4JdTask", method = RequestMethod.POST)
	public void dealItemAndChannelItem4JdTask(@RequestBody JdCommonParam jdCommonParam, @RequestBody GlobalShopItemVo globalShopItemVo);

	//一键分享的首页商品列表
	@RequestMapping(value = "/queryItemByStatus", method = RequestMethod.POST)
	List<ItemDO> queryItemByStatus(
			@RequestParam("companyNo") String companyNo,
			@RequestParam("status") String status,
			@RequestParam("start") int start,
			@RequestParam("pageSize") int pageSize);

	//一键分享搜索商品
	@RequestMapping(value = "/queryItemByKeyWord", method = RequestMethod.POST)
	List<ItemDO> queryItemByKeyWord(
			@RequestBody List<String> keyWord,
			@RequestParam("companyNo") String companyNo,
			@RequestParam("start") int start,
			@RequestParam("pageSize") int pageSize);

	//一键分享商品详情
	@RequestMapping(value = "/itemDetailByItemCode", method = RequestMethod.POST)
	ItemDO itemDetailByItemCode(
			@RequestParam("itemCode") String itemCode,
			@RequestParam("companyNo") String companyNo);

	//一键分享，获取商品的图片
	@RequestMapping(value = "/queryItemPicByItemCode", method = RequestMethod.POST)
	String queryItemPicByItemCode(
			@RequestParam("itemCode") String itemCode);

	//一键分享，获取商品的图片
	@RequestMapping(value = "/queryItemPicByItemCodeAndCompanyNo", method = RequestMethod.POST)
	String queryItemPicByItemCodeAndCompanyNo(
			@RequestParam("itemCode") String itemCode,
			@RequestParam("companyNo") String companyNo);


	/**
	 * 导入商品列表
	 * @param list
	 */
	@RequestMapping(value = "/importItem", method = RequestMethod.POST)
	void importItem(@RequestBody List<List<Object>> list) throws Exception;



	//第二部分：
}
