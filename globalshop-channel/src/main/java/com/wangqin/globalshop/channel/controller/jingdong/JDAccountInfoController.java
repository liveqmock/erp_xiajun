package com.wangqin.globalshop.channel.controller.jingdong;

import com.alibaba.fastjson.JSON;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.seller.ShopSafService.ShopJosResult;
import com.jd.open.api.sdk.request.seller.VenderShopQueryRequest;
import com.jd.open.api.sdk.response.seller.VenderShopQueryResponse;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.JingDong.JingdongOauth;
import com.wangqin.globalshop.channel.service.jingdong.GlobalshopStatic;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/jd")
public class JDAccountInfoController {

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private JdShopOauthService jdShopOauthService;

	@Autowired
	private JdShopConfigService jdShopConfigService;
						


	@RequestMapping("/accountInfo")
	@ResponseBody
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
		shopOauth.setExpiresTime(new Date(jdOauthResponse.getTime() + jdOauthResponse.getExpires_in() * 1000));//测试token时效1天，生产1年
		shopOauth.setRefreshToken(jdOauthResponse.getRefresh_token());

		shopOauth.setServerUrl("https://api.jd.com/routerjson");
		shopOauth.setAppKey("96C38E0AAAA47520B6211D32A5A14EDE");
		shopOauth.setAppsecretKey("758ae3185aec4822aa5593fda0aa9b98");
		shopOauth.setTokenUrl("https://oauth.jd.com/oauth/token");


		//组装channelShop
		ChannelShopDO channelShop = new ChannelShopDO();
		channelShop.setChannelNo(String.valueOf(ChannelType.JingDong.getValue()));
		channelShop.setCompanyNo(state);
		channelShop.setShopCode(jdOauthResponse.getUid());
		channelShop.setShopName(jdOauthResponse.getUser_nick());
		channelShop.setExpiresTime(shopOauth.getExpiresTime());
		channelShop.setProxyUrl(GlobalshopStatic.jd_server_url);

		try {
			ShopJosResult shopJosResult = getVenderInfo(shopOauth);

			shopOauth.setShopCode(shopJosResult.getVenderId()+"");

			channelShop.setShopName(shopJosResult.getShopName());
			channelShop.setShopCode(shopJosResult.getVenderId()+"");
			//创建或更新jd_shop_oauth
			jdShopOauthService.createOrUpdateShopOauth(ChannelType.JingDong,shopOauth);
			//创建或更新jd_shop_config
			jdShopConfigService.initShopConfig(ChannelType.JingDong,shopOauth);
			//下发创建或更新  channel_shop
			Map<String,String> pram = new HashMap<>();
			pram.put("channelShop",JSON.toJSONString(channelShop));
			HttpClientUtil.post("http://test.buyer007.cn/channelshop/addOrupdate",pram);
		} catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("授权失败, "+e.getErrorCode()+"  "+e.getErrorMsg());
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg("授权失败,jsonStr:" + jsonStr+"  "+e.getMessage());
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


	private ShopJosResult getVenderInfo(JdShopOauthDO shopOauth){

		JdClient client = new DefaultJdClient(shopOauth.getServerUrl(),shopOauth.getAccessToken(),shopOauth.getAppKey(),shopOauth.getAppsecretKey());

		VenderShopQueryRequest request=new VenderShopQueryRequest();
		VenderShopQueryResponse response = null;
		try {
			response=client.execute(request);
		} catch (JdException e) {
			logger.error("getVenderInfo_error",e);
			throw new ErpCommonException("getVenderInfo,获取店铺信息时，京东内部出错");
		}
		if(!"0".equals(response.getCode())){
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc();
			throw new ErpCommonException("获取店铺信息时，京东内部出错:"+errorMsg);
		}
		return response.getShopJosResult();
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
//		shopOauth.setServerUrl("https://api.jd.com/routerjson");
//		shopOauth.setAppKey("96C38E0AAAA47520B6211D32A5A14EDE");
//		shopOauth.setAppsecretKey("758ae3185aec4822aa5593fda0aa9b98");
//		shopOauth.setTokenUrl("https://oauth.jd.com/oauth/token");
//
//		ShopJosResult shopJosResult = getVenderInfo(shopOauth);
//		shopOauth.setShopCode(shopJosResult.getVenderId()+"");
//
//
//
//
//		//创建或更新jd_shop_oauth
//		jdShopOauthService.createOrUpdateShopOauth(ChannelType.JingDong,shopOauth);
//
//		//创建或更新jd_shop_config
//		jdShopConfigService.initShopConfig(ChannelType.JingDong,shopOauth);
//
//	}

//	@RequestMapping("/shoptest")
//	public void shoptest(String state, String jsonStr){
//
//		JingdongOauth jdOauthResponse = JSON.parseObject(jsonStr, JingdongOauth.class);
//
//		ChannelShopDO channelShop = new ChannelShopDO();
//		channelShop.setChannelNo(String.valueOf(ChannelType.JingDong.getValue()));
//		channelShop.setCompanyNo(state);
//		channelShop.setShopCode(jdOauthResponse.getUid());
//		channelShop.setShopName(jdOauthResponse.getUser_nick());
//		channelShop.setExpiresTime(new Date());
//
//		Map<String,String> pram = new HashMap<>();
//		pram.put("channelShop",JSON.toJSONString(channelShop));
//		try {
//			JSONObject json = HttpClientUtil.post("http://localhost:8100/channelshop/addOrupdate",pram);
//		}catch (exception e){
//
//		}
//
//	}


}
