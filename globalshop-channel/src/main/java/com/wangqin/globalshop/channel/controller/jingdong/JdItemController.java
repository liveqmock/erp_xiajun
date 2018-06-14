package com.wangqin.globalshop.channel.controller.jingdong;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.*;
import com.wangqin.globalshop.channelapi.dal.ChannelListingItemVo;
import com.wangqin.globalshop.channelapi.dal.ChannelSalePriceVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by 777 on 2018/6/13
 */

@Controller
@RequestMapping("/jd")
public class JdItemController extends BaseController {

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private JdShopOauthService jdShopOauthService;

	private JdItemOperateService jdItemOperateService;




	@RequestMapping("/addItem")
	@ResponseBody
	public Object addItem(@RequestBody JdCommonParam jdCommonParam, @RequestParam String itemCode)  {

		JsonResult<String> result = new JsonResult<>();

		if(jdCommonParam == null
				|| EasyUtil.isStringEmpty(jdCommonParam.getChannelNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getCompanyNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getShopCode())){
			return result.buildIsSuccess(false).buildMsg("JdCommonParam 参数为空");
		}

		JdShopOauthDO shopOauth = jdShopOauthService.searchShopOauthByCCS(jdCommonParam.getChannelNo(),jdCommonParam.getCompanyNo(),jdCommonParam.getShopCode());

		if(shopOauth == null){
			return result.buildIsSuccess(false).buildMsg("未找到相关的店铺："+jdCommonParam.getChannelNo()+"-"+jdCommonParam.getCompanyNo()+"-"+jdCommonParam.getShopCode());
		}


		try {
			jdItemOperateService.createItemOpreate(shopOauth,OperateType.OPERATE_ADD,itemCode,SyncStatus.REQUEST,null);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}

	@RequestMapping("/updateItem")
	@ResponseBody
	public Object updateItem(@RequestBody JdCommonParam jdCommonParam, @RequestParam String itemCode)  {

		JsonResult<String> result = new JsonResult<>();

		if(jdCommonParam == null
				|| EasyUtil.isStringEmpty(jdCommonParam.getChannelNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getCompanyNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getShopCode())){
			return result.buildIsSuccess(false).buildMsg("JdCommonParam 参数为空");
		}

		JdShopOauthDO shopOauth = jdShopOauthService.searchShopOauthByCCS(jdCommonParam.getChannelNo(),jdCommonParam.getCompanyNo(),jdCommonParam.getShopCode());

		if(shopOauth == null){
			return result.buildIsSuccess(false).buildMsg("未找到相关的店铺："+jdCommonParam.getChannelNo()+"-"+jdCommonParam.getCompanyNo()+"-"+jdCommonParam.getShopCode());
		}

		try {
			jdItemOperateService.createItemOpreate(shopOauth,OperateType.OPERATE_UPDATE,itemCode,SyncStatus.REQUEST,null);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}


	@RequestMapping("/listingItem")
	@ResponseBody
	public Object listingItem(@RequestBody JdCommonParam jdCommonParam, @RequestBody ChannelListingItemVo channelListingItemVo)  {

		JsonResult<String> result = new JsonResult<>();

		if(jdCommonParam == null
				|| EasyUtil.isStringEmpty(jdCommonParam.getChannelNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getCompanyNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getShopCode())){
			return result.buildIsSuccess(false).buildMsg("JdCommonParam 参数为空");
		}

		JdShopOauthDO shopOauth = jdShopOauthService.searchShopOauthByCCS(jdCommonParam.getChannelNo(),jdCommonParam.getCompanyNo(),jdCommonParam.getShopCode());

		if(shopOauth == null){
			return result.buildIsSuccess(false).buildMsg("未找到相关的店铺："+jdCommonParam.getChannelNo()+"-"+jdCommonParam.getCompanyNo()+"-"+jdCommonParam.getShopCode());
		}

		try {
			JdShopFactory.getChannel(shopOauth).listingItem(channelListingItemVo);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}

	@RequestMapping("/delistingItem")
	@ResponseBody
	public Object delistingItem(@RequestBody JdCommonParam jdCommonParam, @RequestBody ChannelListingItemVo channelListingItemVo)  {

		JsonResult<String> result = new JsonResult<>();

		if(jdCommonParam == null
				|| EasyUtil.isStringEmpty(jdCommonParam.getChannelNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getCompanyNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getShopCode())){
			return result.buildIsSuccess(false).buildMsg("JdCommonParam 参数为空");
		}

		JdShopOauthDO shopOauth = jdShopOauthService.searchShopOauthByCCS(jdCommonParam.getChannelNo(),jdCommonParam.getCompanyNo(),jdCommonParam.getShopCode());

		if(shopOauth == null){
			return result.buildIsSuccess(false).buildMsg("未找到相关的店铺："+jdCommonParam.getChannelNo()+"-"+jdCommonParam.getCompanyNo()+"-"+jdCommonParam.getShopCode());
		}

		try {
			JdShopFactory.getChannel(shopOauth).delistingItem(channelListingItemVo);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}

	@RequestMapping("/modifySalePrice")
	@ResponseBody
	public Object modifySalePrice(@RequestBody JdCommonParam jdCommonParam, @RequestBody ChannelSalePriceVo channelSalePriceVo)  {

		JsonResult<String> result = new JsonResult<>();

		if(jdCommonParam == null
				|| EasyUtil.isStringEmpty(jdCommonParam.getChannelNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getCompanyNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getShopCode())){
			return result.buildIsSuccess(false).buildMsg("JdCommonParam 参数为空");
		}

		JdShopOauthDO shopOauth = jdShopOauthService.searchShopOauthByCCS(jdCommonParam.getChannelNo(),jdCommonParam.getCompanyNo(),jdCommonParam.getShopCode());

		if(shopOauth == null){
			return result.buildIsSuccess(false).buildMsg("未找到相关的店铺："+jdCommonParam.getChannelNo()+"-"+jdCommonParam.getCompanyNo()+"-"+jdCommonParam.getShopCode());
		}

		try {
			JdShopFactory.getChannel(shopOauth).modifySalePrice(channelSalePriceVo);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}

}
