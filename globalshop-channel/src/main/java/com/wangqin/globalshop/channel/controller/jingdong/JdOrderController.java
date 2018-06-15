package com.wangqin.globalshop.channel.controller.jingdong;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdLogisticsDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.jingdong.JdShopFactory;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by 777 on 2018/6/13
 */
@Controller
@RequestMapping("/jdorder")
public class JdOrderController {

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private JdShopOauthService jdShopOauthService;

	@RequestMapping("/logisticComfire")
	@ResponseBody
	public Object logisticComfire(@RequestBody JdCommonParam jdCommonParam, @RequestBody JdLogisticsDO jdLogisticsDO)  {

		JsonResult<String> result = new JsonResult<>();

		if(jdCommonParam == null
				|| EasyUtil.isStringEmpty(jdCommonParam.getChannelNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getCompanyNo())
				|| EasyUtil.isStringEmpty(jdCommonParam.getShopCode())){
			return result.buildIsSuccess(false).buildMsg("JdCommonParam 参数为空");
		}

		JdShopOauthDO shopOauth = jdShopOauthService.searchShopOauthByCCS(jdCommonParam.getChannelNo(),jdCommonParam.getCompanyNo(),jdCommonParam.getShopCode());

		if(shopOauth == null){
			return result.buildIsSuccess(false).buildMsg("未找到相关的店铺："+jdCommonParam.getChannelNo()+"-"+jdCommonParam.getCompanyNo()+"-"+jdCommonParam.getShopCode());
		}

		try {
			JdShopFactory.getChannel(shopOauth).logisticComfire(jdLogisticsDO);
		}catch (ErpCommonException e){
			return result.buildIsSuccess(false).buildMsg("错误："+e.getErrorMsg());
		}catch (Exception e){
			logger.error("",e);
			return result.buildIsSuccess(false).buildMsg("内部错误："+e.getMessage());
		}

		return result.buildIsSuccess(true).buildMsg("成功");
	}

}
