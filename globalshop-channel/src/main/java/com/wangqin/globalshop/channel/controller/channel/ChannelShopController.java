package com.wangqin.globalshop.channel.controller.channel;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
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


}
