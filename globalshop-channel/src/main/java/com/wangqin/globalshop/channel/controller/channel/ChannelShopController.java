package com.wangqin.globalshop.channel.controller.channel;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.service.IdCardService;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.channel.ChannelShopService;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */

@Controller
@RequestMapping("/channelshop")
@Authenticated
public class ChannelShopController extends BaseController {

	@Value("#{sys.youzan_redirect_uri}")
	public String youzan_redirect_uri;

	@Autowired
	private ChannelShopService channelShopService;


	@Autowired
	private IdCardService idCardService;


	@RequestMapping("/addOrupdate")
	@ResponseBody
	public Object addOrUpdateShop(String channelShop){
		JsonResult<String> result = null;
		try {
			ChannelShopDO  channelShopIn = JSON.parseObject(channelShop,ChannelShopDO.class);
			result = new JsonResult<>();
			channelShopService.createOrUpdate(channelShopIn);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(""+e.getMessage());
		}
		return result.buildIsSuccess(true);
	}


	@RequestMapping("/queryChannelList")
	@ResponseBody
	public Object queryChannelList(){
		JsonResult<List<ChannelShopDO>> result = new JsonResult<>();
		try {
			ChannelShopDO channelShopDO = new ChannelShopDO();
			channelShopDO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
			List<ChannelShopDO> shopDOList = channelShopService.searchShopList(channelShopDO);
			result.setData(shopDOList);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(""+e.getMessage());
		}
		return result.buildIsSuccess(true);
	}

	@RequestMapping("/getOauthUrl")
	@ResponseBody
	public Object getOauthUrl(){
		JsonResult<String> result = new JsonResult<>();
		String companyNo = AppUtil.getLoginUserCompanyNo();

		String url ="https://open.youzan.com/oauth/authorize?client_id=29f22f7d615e50079f&response_type=code&redirect_uri="+youzan_redirect_uri+"&state=";

		result.buildData(url+companyNo);
		return result.buildIsSuccess(true);
	}



	@RequestMapping("/testidcard")
	@ResponseBody
	public Object testidcard(String name , String idNumber){
		JsonResult<String> result = new JsonResult<>();
		Boolean isTrue = idCardService.idcardTwoItem(name,idNumber);
		result.buildData(isTrue+"");
		return result.buildIsSuccess(true);
	}


	@RequestMapping("/addhaihu")
	@ResponseBody
	public Object addhaihu(){
		JsonResult<ChannelShopDO> result = null;
		try {
			result = new JsonResult<>();
			ChannelShopDO channelShop = channelShopService.addhaihu();
			result.buildData(channelShop);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(""+e.getMessage());
		}
		return result.buildIsSuccess(true);
	}


	/**
	 * 编辑，或确认开通店铺
	 * @param channelShop
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object addOrUpdateShop(ChannelShopDO channelShop){
		JsonResult<String> result = null;
		try {
			result = new JsonResult<>();
			channelShopService.checkUnique(channelShop);
			channelShopService.createOrUpdate(channelShop);
		} catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg(""+e.getErrorMsg());
		}catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(""+e.getMessage());
		}
		return result.buildIsSuccess(true);
	}

	/**
	 * 启用，停用店铺
	 * @param
	 * @return
	 */
	@RequestMapping("/changeOpen")
	@ResponseBody
	public Object addOrUpdateShop(String shopCode, Boolean open){
		JsonResult<String> result = null;
		try {
			result = new JsonResult<>();
			channelShopService.changeOpen(shopCode, open);
		} catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg(""+e.getErrorMsg());
		}catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(""+e.getMessage());
		}
		return result.buildIsSuccess(true);
	}

}
