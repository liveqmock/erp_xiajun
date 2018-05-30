package com.wangqin.globalshop.channel.controller.taobao;

import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channel.service.channelAccountConfig.IChannelAccountConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/taobao")
public class TaobaoAccountInfoController {
			
	@Autowired IChannelAccountService channelAccountService;

	@Autowired IChannelAccountConfigService channelAccountConfigService;
						
	@RequestMapping("/accountInfo")
	@ResponseBody
	public void accountInfo(String name, String cookie) throws IOException {

		System.out.print("hhhh");

		//配置店铺授权表
		channelAccountService.createOrupdateAccount4Taobao(name,cookie);

		//配置抓取时间表
		channelAccountConfigService.createOrupdateConfig("1",name);

	}
	

	

	
	
	


}
