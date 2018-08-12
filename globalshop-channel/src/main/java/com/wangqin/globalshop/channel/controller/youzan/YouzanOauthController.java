package com.wangqin.globalshop.channel.controller.youzan;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.youzan.YouzanOauthResponse;
import com.wangqin.globalshop.channel.service.YouzanService;
import com.wangqin.globalshop.channel.service.channel.ChannelShopService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopConfigService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HttpPostUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanShopGetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by 777 on 2018/8/9
 */

@Controller
@RequestMapping("/youzan")
public class YouzanOauthController extends BaseController {


	public static final String youzan_client_id = "29f22f7d615e50079f";

	public static final String youzan_client_secret = "efb0091fe76678322000af822c705c0a";

	public static final String youzan_redirect_uri = "http://47.98.164.133:8100/youzan/oauth/";

	public static final String youzan_token_url = "https://open.youzan.com/oauth/token";

	public static final String youzan_server_url = "";//现在用sdk，不用URL


	@Autowired
	private JdShopOauthService jdShopOauthService;

	@Autowired
	private JdShopConfigService jdShopConfigService;

	@Autowired
	private YouzanService youzanService;

	@Autowired
	private ChannelShopService channelShopService;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@RequestMapping(value = "/oauth", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object oauth(@RequestParam("code") String code, @RequestParam("state") String state) {
		logger.info("=======youzan oauth begin======> code: " + code + " state: "+state);
		JsonResult<String> result = new JsonResult<>();

		if (state != null) {
			state = state.replaceAll(" ", "+");
		}

		// 下面要获取token
		logger.info("youzan oauth code:"+code+"  state:"+state);
		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "authorization_code");
		param.put("code", code);
		param.put("client_id", youzan_client_id);
		param.put("client_secret", youzan_client_secret);
		param.put("redirect_uri", youzan_redirect_uri);

		YouzanOauthResponse oauthResponse = null;
		try {
			logger.info(param);
			String str = HttpPostUtil.doHttpPost(youzan_token_url, param);
			logger.info(str);
			oauthResponse = BaseDto.fromJson(str, YouzanOauthResponse.class);
			if (oauthResponse == null || EasyUtil.isStringEmpty(oauthResponse.getRefresh_token()) || EasyUtil.isStringEmpty(oauthResponse.getAccess_token())) {
				logger.error("使用授权码获取TOKEN时，失败。" + oauthResponse.getError() + ":" + oauthResponse.getError_description());
				throw new ErpCommonException("BAD_CODE", "使用授权码获取TOKEN时，失败。");
			}
		} catch (ErpCommonException e) {
			logger.error(e.getErrorCode() + e.getMessage());
			//request.setAttribute("errorNote", e.getMessage());
			//request.getRequestDispatcher("error.jsp").forward(request, response);
			return result.buildIsSuccess(false).buildMsg("YouzanOauthResponse: " +BaseDto.toString(oauthResponse));
		} catch (Exception e) {
			logger.error("getToken error", e);
			//request.setAttribute("errorNote", "有赞提供的授权码错误，请重新授权，谢谢!"+e.getMessage());
			//request.getRequestDispatcher("error.jsp").forward(request, response);
			return result.buildIsSuccess(false).buildMsg("YouzanOauthResponse: " +BaseDto.toString(oauthResponse));
		}

		//获取店铺信息
		YouzanShopGetResult shopGetResult = youzanService.getShopInfo(oauthResponse);

		if(shopGetResult == null){
             throw new ErpCommonException("YouzanShopGetResult error",BaseDto.toString(oauthResponse));
		}

		JdShopOauthDO shopOauth = new JdShopOauthDO();

		shopOauth.setChannelNo(String.valueOf(ChannelType.YouZan.getValue()));
		shopOauth.setCompanyNo(state);
		shopOauth.setShopCode(shopGetResult.getId()+"");
		shopOauth.setAccessToken(oauthResponse.getAccess_token());

		shopOauth.setGmtModify(new Date());//授权时间
		shopOauth.setExpiresTime(new Date(System.currentTimeMillis() + oauthResponse.getExpires_in() * 1000));//测试token时效1天，生产1年
		shopOauth.setRefreshToken(oauthResponse.getRefresh_token());
        shopOauth.setOpen(true);
		shopOauth.setServerUrl(youzan_server_url);
		shopOauth.setAppKey(youzan_client_id);
		shopOauth.setAppsecretKey(youzan_client_secret);
		shopOauth.setTokenUrl(youzan_token_url);


		//组装channelShop
		ChannelShopDO channelShop = new ChannelShopDO();
		channelShop.setChannelNo(String.valueOf(ChannelType.YouZan.getValue()));
		channelShop.setCompanyNo(state);
		channelShop.setShopCode(shopOauth.getShopCode());
		channelShop.setShopName(shopGetResult.getName());
		channelShop.setExpiresTime(shopOauth.getExpiresTime());
		channelShop.setProxyUrl(shopGetResult.getLogo());
		channelShop.setOpen(true);
		try {
			transactionTemplate.execute(new TransactionCallback<Boolean>() {
				@Override
				public Boolean doInTransaction(TransactionStatus transactionStatus) {
					doOauth(shopOauth,channelShop);
					return Boolean.TRUE;
				}
			});

//			Map<String,String> pram = new HashMap<>();
//			pram.put("channelShop",JSON.toJSONString(channelShop));
//			HttpClientUtil.post("http://test.buyer007.cn/channelshop/addOrupdate",pram);
		} catch (ErpCommonException e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("授权失败, "+e.getErrorCode()+"  "+e.getErrorMsg());
		} catch (Exception e) {
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("授权失败,"+e.getMessage());
		}
		return result.buildIsSuccess(true).buildMsg("授权成功");
	}

	private void doOauth(JdShopOauthDO shopOauth, ChannelShopDO channelShop){
		//创建或更新jd_shop_oauth
		jdShopOauthService.createOrUpdateShopOauth(ChannelType.YouZan,shopOauth);
		//创建或更新jd_shop_config
		jdShopConfigService.initShopConfig(ChannelType.YouZan,shopOauth);
		//下发创建或更新  channel_shop
		channelShopService.createOrUpdate(channelShop);
	}



}
