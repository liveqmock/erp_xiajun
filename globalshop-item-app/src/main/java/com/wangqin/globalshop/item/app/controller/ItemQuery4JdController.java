package com.wangqin.globalshop.item.app.controller;

import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.channelapi.dal.GlobalShopItemVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.item.app.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping("/queryadd")
	@ResponseBody
	public Object queryAdd(String itemCode){

		ItemVo itemVo = iItemService.queryAdd(itemCode);
		JsonResult<ItemVo> result = new JsonResult<>();
		result.setData(itemVo);
		result.setSuccess(true);
		result.setMsg("成功");
		return result;

	}

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


	@RequestMapping("/additem")
	@ResponseBody
	public Object addItem(@RequestBody JdCommonParam jdCommonParam, @RequestBody GlobalShopItemVo globalShopItemVo){

		JsonResult<GlobalShopItemVo> result = new JsonResult<>();

//		GlobalShopItemVo globalShopItemVo = null;
//		try {
//			globalShopItemVo = iItemService.queryUpdate(itemCode,shopCode);
//		} catch (Exception e) {
//			return result.buildIsSuccess(false).buildMsg("查询失败"+e.getMessage());
//		}

		result.setData(globalShopItemVo);
		result.setSuccess(true);
		result.setMsg("成功");
		return result;

	}











}
