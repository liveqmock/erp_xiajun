package com.wangqin.globalshop.order.app.controller.jingdong;

import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.channelapi.dal.GlobalshopOrderVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by 777 on 2018/6/14
 */

@Controller
@RequestMapping(value = "jdorder")
public class MallOrder4JdController {


	@Autowired
	private IMallOrderService mallOrderService;

	@RequestMapping("/dealOrder")
	@ResponseBody
	public Object dealOrder(@RequestBody JdCommonParam jdCommonParam, @RequestBody GlobalshopOrderVo globalshopOrderVo){

		JsonResult<String> result = new JsonResult<>();
		try {
			mallOrderService.dealOrder(jdCommonParam,globalshopOrderVo);
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg("失败："+e.getMessage());
		}
		result.setSuccess(true);
		result.setMsg("成功");
		return result;

	}


}
