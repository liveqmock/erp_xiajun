package com.wangqin.globalshop.channel.controller.channel;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.channel.service.channel.ChannelShopService;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Object addOrUpdateShop(String channelShopJson){
		JsonResult<String> result = null;
		try {
			ChannelShopDO  channelShop = JSON.parseObject(channelShopJson,ChannelShopDO.class);
			result = new JsonResult<>();
			channelShopService.createOrUpdate(channelShop);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg(""+e.getMessage());
		}
		return result.buildIsSuccess(true);
	}


}
