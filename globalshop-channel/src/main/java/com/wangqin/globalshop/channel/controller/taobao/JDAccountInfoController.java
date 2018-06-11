package com.wangqin.globalshop.channel.controller.taobao;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelAccountDOMapperExt;
import com.wangqin.globalshop.channel.dal.JingDong.JingdongOauth;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Controller
@RequestMapping("/jd")
public class JDAccountInfoController {
			
	@Autowired
	private JdShopOauthService jdShopOauthService;

	@Autowired
	private JdShopConfigService jdShopConfigService;
						


	@RequestMapping("/accountInfo")
	public void accountInfo(String code, String state) throws IOException{

		// 用来获取token的CODE
		String tokenCode = code;
//		if (tokenCode == null) {
//			request.setAttribute("errorNote", "京东提供的<font color='red'>授权码</font>不正确!<br/>请重新返回京东，重新授权，谢谢!");
//			request.getRequestDispatcher("error.jsp").forward(request, response);
//			return;
//		}

		// 浏览器交互时，可能会把'+'换成' ',所以要把' '换成'+'
		if (state != null) {
			state = state.replaceAll(" ", "+");
		}
		System.out.println(state);


		// 下面要获取token
		String url = "https://oauth.jd.com/oauth/token?grant_type=authorization_code&client_id=" + "96C38E0AAAA47520B6211D32A5A14EDE" + "&client_secret=" + "758ae3185aec4822aa5593fda0aa9b98"
				+ "&scope=read&redirect_uri=" + "http://test.buyer007.cn/jd/accountInfo" + "&code=" + tokenCode + "&state="
				+ state;
		URL uri = new URL(url);


		insertIntoChannelAccount4Test(url,null,state);

		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestMethod("POST");
		int refreshCode = conn.getResponseCode();
		InputStream is = conn.getInputStream();
		String jsonStr = inputStream2String(is, conn.getContentType());
		System.out.println("jsonStr");

		insertIntoChannelAccount4Test(url,jsonStr,state);



		JingdongOauth jdOauthResponse = JSON.parseObject(jsonStr, JingdongOauth.class);

		JdShopOauthDO shopOauth = new JdShopOauthDO();

		//shopOauth.setGmtCreate(new Date());
		shopOauth.setChannelNo(String.valueOf(ChannelType.JingDong.getValue()));
		shopOauth.setCompanyNo(state);

		shopOauth.setShopCode(jdOauthResponse.getUid());
		shopOauth.setAccessToken(jdOauthResponse.getAccess_token());

		shopOauth.setGmtModify(new Date());//授权时间
		shopOauth.setExpiresTime(new Date(jdOauthResponse.getTime() + jdOauthResponse.getExpires_in() * 1000));
		shopOauth.setRefreshToken(jdOauthResponse.getRefresh_token());


		//创建或更新jd_shop_oauth
		jdShopOauthService.createOrUpdateShopOauth(ChannelType.JingDong,shopOauth);

		//创建或更新jd_shop_config
		jdShopConfigService.initShopConfig(ChannelType.JingDong,shopOauth);

		//下发创建或更新  channel_shop



	}

	public static String inputStream2String(InputStream is, String charset) throws IOException {
		charset = "GBK";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString(charset);
	}



	@Autowired
	private ChannelAccountDOMapperExt channelAccountDOMapper;


	private void insertIntoChannelAccount4Test(String url, String  jsonStr, String state){

		ChannelAccountDO channelAccount = new ChannelAccountDO();

		//渠道信息
		channelAccount.setChannelId(Long.valueOf(ChannelType.JingDong.getValue()));
		channelAccount.setChannelNo(ChannelType.JingDong.getValue()+"");
		channelAccount.setType(ChannelType.JingDong.getValue());
		channelAccount.setChannelName(ChannelType.JingDong.getName());

		//company信息，所属域
		channelAccount.setCompanyNo(state);

		//授权信息
		channelAccount.setShopCode(state);
		channelAccount.setShopName(url);
		channelAccount.setCookie(jsonStr);

		//其他信息配置
		channelAccount.setStatus(0);//0正常，1关闭
		channelAccount.init();

		channelAccountDOMapper.insert(channelAccount);

	}
	
	


}
