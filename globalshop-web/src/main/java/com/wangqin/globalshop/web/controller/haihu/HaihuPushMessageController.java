package com.wangqin.globalshop.web.controller.haihu;

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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.service.channel.ChannelFactory;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.Md5Util;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/haihuPushMessage")
public class HaihuPushMessageController extends BaseController {

	 /**
     * 海狐推送订单拆单
     * @param outerOrder
     * haihupullOrder
     * @return
     */
	/**
     * 海狐推送订单
     * @param outerOrder
     * haihupullOrder
     * @return
     */
	@RequestMapping(value = "/haihupullOrder*")
	public void pullOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {





		try {
			ChannelFactory.getChannel("1", ChannelType.HaiHu).syncOrder(request, response);
		} catch (Exception e) {
			response.getWriter().print("系统异常");
			logger.error("haihupullOrder* 异常" + request.getRequestURI(), e);
		}
	}
	
	/*
	 * 海狐请求商品
	 */
	@RequestMapping(value = "/queryHaiHuItem")
	public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			ChannelFactory.getChannel("1", ChannelType.HaiHu).syncItem(request, response);
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
				.post("http://localhost:8080/haierp1/haihuPushMessage/queryHaiHuItem", null, param,"1");
		System.err.println(description);
	}

}
