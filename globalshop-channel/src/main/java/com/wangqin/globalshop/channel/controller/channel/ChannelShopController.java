package com.wangqin.globalshop.channel.controller.channel;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.service.IdCardService;
import com.wangqin.globalshop.channel.service.channel.ChannelShopService;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
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



	@Autowired
	private ChannelShopService channelShopService;


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

		String url ="https://open.youzan.com/oauth/authorize?client_id=29f22f7d615e50079f&response_type=code&redirect_uri=http://47.98.164.133:8100/youzan/oauth/&state=";

		result.buildData(url+companyNo);
		return result.buildIsSuccess(true);
	}


	@Autowired
	private IdCardService idCardService;


	@RequestMapping("/testidcard")
	@ResponseBody
	public Object testidcard(String name , String idNumber){
		JsonResult<String> result = new JsonResult<>();
		Boolean isTrue = idCardService.idcardTwoItem(name,idNumber);
		result.buildData(isTrue+"");
		return result.buildIsSuccess(true);
	}

}
