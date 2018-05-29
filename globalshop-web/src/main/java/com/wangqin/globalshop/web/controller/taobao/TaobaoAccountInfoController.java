package com.wangqin.globalshop.web.controller.taobao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.biz1.app.service.channelAccountConfig.IChannelAccountConfigService;

@Controller
@RequestMapping("/taobao")
public class TaobaoAccountInfoController {
			
	@Autowired IChannelAccountService channelAccountService;

	@Autowired IChannelAccountConfigService channelAccountConfigService;
						
	@RequestMapping("/accountInfo")
	@ResponseBody
	public void accountInfo(String name, String cookie) throws IOException {

		//配置店铺授权表
		channelAccountService.createOrupdateAccount4Taobao(name,cookie);

		//配置抓取时间表
		channelAccountConfigService.createOrupdateConfig("1",name);

	}
	

	

	
	
	


}
