package com.wangqin.globalshop.channel.controller.haihu;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.channel.service.channel.ChannelFactory;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.Md5Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/haihuPushMessage")
public class HaihuPushMessageController extends BaseController {

	@Autowired
	private JdShopOauthService shopOauthService;

	@Autowired
	private ChannelCommonService channelCommonService;

	/**
	 *  海狐推送订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/haihupullOrder/*")
	public void pullOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.HaiHu.getValue()+"");
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);


		//jdshopAouth只有一个海狐的授权，默认查询不会按照以前的信息进行处理
		if(EasyUtil.isListEmpty(shopOauthDOList)){
			response.getWriter().print("系统异常,未找到授权信息");
		}


		if(!EasyUtil.isListEmpty(shopOauthDOList) && shopOauthDOList.size() ==1){
			try {
				ChannelFactory.getChannel(shopOauthDOList.get(0)).syncOrder(request, response);
			} catch (Exception e) {
				response.getWriter().print("系统异常");
				logger.error("haihupullOrder* 异常" + request.getRequestURI(), e);
			}
			return;
		}
		logger.error("channel_shop_error","海狐渠道未配置账号");


	}
	
	/*
	 * 海狐请求商品
	 */
	@RequestMapping(value = "/queryHaiHuItem")
	public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setChannelNo(ChannelType.HaiHu.getValue()+"");
		List<JdShopOauthDO> shopOauthDOList = shopOauthService.searchShopOauthList(shopOauthSo);

		//jdshopAouth只有一个海狐的授权，默认查询不会按照以前的信息进行处理
		if(EasyUtil.isListEmpty(shopOauthDOList)){
			response.getWriter().print("系统异常,未找到授权信息");
		}

		try {
			ChannelFactory.getChannel(shopOauthDOList.get(0)).syncItem(request, response);
		} catch (Exception e) {
			response.getWriter().print("系统异常");
			logger.error("queryHaiHuItem 异常", e);
		}
	}
	
	public static void main(String[] args) throws ParseException{
		Map<String, Object> param = new HashMap<String, Object>();
		List<Map<String, Object>> paramDetailList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStamp = dateFormat.format(new Date());
		String sign = Md5Util.getMD5("enteCode=haihuhaitao&timeStamp="+timeStamp);
		param.put("timeStamp", timeStamp);
		param.put("enteCode", "haihuhaitao");
		param.put("sign", sign);
		JSONObject json = JSONObject.fromObject(param);
		System.out.println(json);
		JSONObject description = HttpClientUtil
				.post("http://localhost:8100/haierp1/haihuPushMessage/queryHaiHuItem", null, param,"1");
		System.err.println(description);
	}

}
