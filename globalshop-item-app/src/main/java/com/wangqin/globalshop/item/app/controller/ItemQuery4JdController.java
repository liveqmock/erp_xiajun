package com.wangqin.globalshop.item.app.controller;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.channelapi.dal.GlobalShopItemVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.item.app.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 这个接口专门为渠道子系统设计，不需要权限校验
 * Create by 777 on 2018/6/13
 */

@Controller
@RequestMapping("/jditem")
public class ItemQuery4JdController {


	@Autowired
	private IItemService iItemService;

	//已完成
	@RequestMapping("/queryadd")
	@ResponseBody
	public Object queryAdd(String itemCode){



//		ItemVo itemVo = iItemService.queryAdd(itemCode);
//
//		return JSON.toJSONString(itemVo);

		JsonResult<ItemVo> result = new JsonResult<>();

		ItemVo itemVo = null;
		try {
			itemVo = iItemService.queryAdd(itemCode);
		} catch (Exception e) {

			return result.buildIsSuccess(false).buildMsg("失败"+e.getMessage());
		}
		result.setData(itemVo);
		result.buildIsSuccess(true).buildMsg("成功");

		return result;


	}

	//已测试完成
	@RequestMapping("/queryupdate")
	@ResponseBody
	public Object queryUpdate(String itemCode, String shopCode){

		JsonResult<GlobalShopItemVo> result = new JsonResult<>();

		GlobalShopItemVo globalShopItemVo = null;
		try {
			globalShopItemVo = iItemService.queryUpdate(itemCode,shopCode);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg("查询失败"+e.getMessage());
		}

		result.setData(globalShopItemVo);
		result.setSuccess(true);
		result.setMsg("成功");
		return result;

	}


	/**
	 * 上新商品后的返回值
	 * @param jdCommonParam
	 * @param globalShopItemVo
	 * @return
	 */
	@RequestMapping("/additem")
	@ResponseBody
	public Object addItem(String  jdCommonParam, String  globalShopItemVo){
		JdCommonParam jdCommon = JSON.parseObject(jdCommonParam,JdCommonParam.class);
		GlobalShopItemVo globalShopItem = JSON.parseObject(globalShopItemVo,GlobalShopItemVo.class);
		JsonResult<String> result = new JsonResult<>();
		try {
			iItemService.dealItemAndChannelItem4JdAdd(jdCommon,globalShopItem);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg("失败："+e.getMessage());
		}
		result.setSuccess(true);
		result.setMsg("成功");
		return result;

	}
	/**
	 * 主动从京东抓取过来的商品下发
	 * @param jdCommonParam
	 * @param globalShopItemVo
	 * @return
	 */
	@RequestMapping("/taskitem")
	@ResponseBody
	public Object taskitem(String  jdCommonParam, String  globalShopItemVo){
		JdCommonParam jdCommon = JSON.parseObject(jdCommonParam,JdCommonParam.class);
		GlobalShopItemVo globalShopItem = JSON.parseObject(globalShopItemVo,GlobalShopItemVo.class);

		JsonResult<String> result = new JsonResult<>();
		try {
			iItemService.dealItemAndChannelItem4JdTask(jdCommon,globalShopItem);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg("失败："+e.getMessage());
		}
		result.setSuccess(true);
		result.setMsg("成功");
		return result;

	}











}
