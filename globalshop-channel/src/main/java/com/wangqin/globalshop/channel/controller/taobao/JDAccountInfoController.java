package com.wangqin.globalshop.channel.controller.taobao;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.dal.JingDong.JingdongOauth;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.common.utils.JsonResult;
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
	public Object accountInfo(String code, String state) throws IOException{
		JsonResult<String> result = new JsonResult<>();
		// 用来获取token的CODE
		String tokenCode = code;

		if (state != null) {
			state = state.replaceAll(" ", "+");
		}

		// 下面要获取token
		String url = "https://oauth.jd.com/oauth/token?grant_type=authorization_code&client_id=" + "96C38E0AAAA47520B6211D32A5A14EDE" + "&client_secret=" + "758ae3185aec4822aa5593fda0aa9b98"
				+ "&scope=read&redirect_uri=http://" + "test.buyer007.cn/jd/accountInfo" + "&code=" + tokenCode + "&state="
				+ state;
		URL uri = new URL(url);

		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestMethod("POST");
		int refreshCode = conn.getResponseCode();
		InputStream is = conn.getInputStream();
		String jsonStr = inputStream2String(is, conn.getContentType());

		JingdongOauth jdOauthResponse = null;
		try {
			jdOauthResponse = JSON.parseObject(jsonStr, JingdongOauth.class);
		}catch (Exception e){
			return result.buildIsSuccess(false).buildMsg("失败,jsonStr:" + jsonStr);
		}

		JdShopOauthDO shopOauth = new JdShopOauthDO();


		shopOauth.setChannelNo(String.valueOf(ChannelType.JingDong.getValue()));
		shopOauth.setCompanyNo(state);

		shopOauth.setShopCode(jdOauthResponse.getUid());
		shopOauth.setAccessToken(jdOauthResponse.getAccess_token());

		shopOauth.setGmtModify(new Date());//授权时间
		shopOauth.setExpiresTime(new Date(jdOauthResponse.getTime() + jdOauthResponse.getExpires_in() * 1000));
		shopOauth.setRefreshToken(jdOauthResponse.getRefresh_token());
		try {
			//创建或更新jd_shop_oauth
			jdShopOauthService.createOrUpdateShopOauth(ChannelType.JingDong,shopOauth);
			//创建或更新jd_shop_config
			jdShopConfigService.initShopConfig(ChannelType.JingDong,shopOauth);
			//下发创建或更新  channel_shop

		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg("失败,jsonStr:" + jsonStr+"  "+e.getMessage());
		}
		return result.buildIsSuccess(true).buildMsg("授权成功");
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






//	@RequestMapping("/accountInfotest")
//	public void accountInfotest(String state, String jsonStr){
//
//		JingdongOauth jdOauthResponse = JSON.parseObject(jsonStr, JingdongOauth.class);
//
//		JdShopOauthDO shopOauth = new JdShopOauthDO();
//
//		//shopOauth.setGmtCreate(new Date());
//		shopOauth.setChannelNo(String.valueOf(ChannelType.JingDong.getValue()));
//		shopOauth.setCompanyNo(state);
//
//		shopOauth.setShopCode(jdOauthResponse.getUid());
//		shopOauth.setAccessToken(jdOauthResponse.getAccess_token());
//
//		shopOauth.setGmtModify(new Date());//授权时间
//		shopOauth.setExpiresTime(new Date(jdOauthResponse.getTime() + jdOauthResponse.getExpires_in() * 1000));
//		shopOauth.setRefreshToken(jdOauthResponse.getRefresh_token());
//
//
//		//创建或更新jd_shop_oauth
//		jdShopOauthService.createOrUpdateShopOauth(ChannelType.JingDong,shopOauth);
//
//		//创建或更新jd_shop_config
//		jdShopConfigService.initShopConfig(ChannelType.JingDong,shopOauth);
//
//	}
//
	


}
