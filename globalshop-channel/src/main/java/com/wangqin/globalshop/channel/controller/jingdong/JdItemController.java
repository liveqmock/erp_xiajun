package com.wangqin.globalshop.channel.controller.jingdong;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Create by 777 on 2018/6/13
 */

@Controller
@RequestMapping("/jd")
public class JdItemController extends BaseController {

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private JdShopOauthService jdShopOauthService;

	@Autowired
	private JdItemOperateService jdItemOperateService;




	//已测试
	@RequestMapping("/addItem")
	@ResponseBody
	public Object addItem(String jdCommonParam, String itemCode)  {

		JsonResult<String> result = new JsonResult<>();

		JdShopOauthDO shopOauth = null;
		try {
			 shopOauth = getShopOauth(jdCommonParam);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return  result.buildIsSuccess(false).buildMsg(e.getMessage());
		}


		try {
			jdItemOperateService.createOrUpdateItemOpreate(shopOauth,OperateType.OPERATE_ADD,itemCode,SyncStatus.REQUEST,null);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}

	//已测试
	@RequestMapping("/updateItem")
	@ResponseBody
	public Object updateItem(String jdCommonParam, String itemCode)  {

		JsonResult<String> result = new JsonResult<>();

		JdShopOauthDO shopOauth = null;
		try {
			shopOauth = getShopOauth(jdCommonParam);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return  result.buildIsSuccess(false).buildMsg(e.getMessage());
		}

		try {
			jdItemOperateService.createOrUpdateItemOpreate(shopOauth,OperateType.OPERATE_UPDATE,itemCode,SyncStatus.REQUEST,null);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}


	//已成功测试
	@RequestMapping("/listingItem")
	@ResponseBody
	public Object listingItem(String jdCommonParam, String  channelListingItemVo)  {

		JsonResult<String> result = new JsonResult<>();

		JdShopOauthDO shopOauth = null;
		try {
			shopOauth = getShopOauth(jdCommonParam);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return  result.buildIsSuccess(false).buildMsg(e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		ChannelListingItemVo data = null;
		try {
			data = mapper.readValue(channelListingItemVo,ChannelListingItemVo.class);
		} catch (IOException e) {
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		try {
			JdShopFactory.getChannel(shopOauth).listingItem(data);
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
	public Object delistingItem(String jdCommonParam, String channelListingItemVo)  {

		JsonResult<String> result = new JsonResult<>();

		JdShopOauthDO shopOauth = null;
		try {
			shopOauth = getShopOauth(jdCommonParam);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return  result.buildIsSuccess(false).buildMsg(e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		ChannelListingItemVo data = null;
		try {
			data = mapper.readValue(channelListingItemVo,ChannelListingItemVo.class);
		} catch (IOException e) {
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		try {
			JdShopFactory.getChannel(shopOauth).delistingItem(data);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}

	//已测试
	@RequestMapping("/resetSkuInv")
	@ResponseBody
	public Object resetSkuInv(String jdCommonParam, String channelListingItemVo)  {

		JsonResult<String> result = new JsonResult<>();

		JdShopOauthDO shopOauth = null;
		try {
			shopOauth = getShopOauth(jdCommonParam);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return  result.buildIsSuccess(false).buildMsg(e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		ChannelListingItemVo data = null;
		try {
			data = mapper.readValue(channelListingItemVo,ChannelListingItemVo.class);
		} catch (IOException e) {
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		try {
			JdShopFactory.getChannel(shopOauth).resetSkuStockNum(data);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}

	//测试完成
	@RequestMapping("/modifySalePrice")
	@ResponseBody
	public Object modifySalePrice(String jdCommonParam, String channelSalePriceVo)  {

		JsonResult<String> result = new JsonResult<>();

		JdShopOauthDO shopOauth = null;
		try {
			shopOauth = getShopOauth(jdCommonParam);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return  result.buildIsSuccess(false).buildMsg(e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		ChannelSalePriceVo data = null;
		try {
			data = mapper.readValue(channelSalePriceVo,ChannelSalePriceVo.class);
		} catch (IOException e) {
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}


		try {
			JdShopFactory.getChannel(shopOauth).modifySalePrice(data);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}

	private JdShopOauthDO getShopOauth(String jdCommonParam){
		ObjectMapper mapper = new ObjectMapper();
		JdCommonParam jdCommon = null;
		try {
			jdCommon = mapper.readValue(jdCommonParam,JdCommonParam.class);
		} catch (IOException e) {
			logger.error("",e);
		}

		if(jdCommon == null
				|| EasyUtil.isStringEmpty(jdCommon.getChannelNo())
				|| EasyUtil.isStringEmpty(jdCommon.getCompanyNo())
				|| EasyUtil.isStringEmpty(jdCommon.getShopCode())){
			throw new ErpCommonException("JdCommonParam 参数为空");
		}

		JdShopOauthDO shopOauth = jdShopOauthService.searchShopOauthByCCS(jdCommon.getChannelNo(),jdCommon.getCompanyNo(),jdCommon.getShopCode());

		if(shopOauth == null){
			throw new ErpCommonException("未找到相关的店铺："+jdCommon.getChannelNo()+"-"+jdCommon.getCompanyNo()+"-"+jdCommon.getShopCode());

		}
		return shopOauth;
	}

}
