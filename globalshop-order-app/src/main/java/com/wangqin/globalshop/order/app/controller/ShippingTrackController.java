package com.wangqin.globalshop.order.app.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.order.app.service.shipping.ShippingTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/shipping")
public class ShippingTrackController {
	@Autowired
	private ShippingTrackService shippingTrackService;
	
	/**
	 * 根据shippingNo查找物流运行轨迹
	 * @param
	 * @return
	 */
	@RequestMapping("/queryByShippingNo")
	@ResponseBody
	public Object queryShippingTrack(String shippingNo) {
		JsonResult<List<ShippingTrackDO>> result = new JsonResult<>();
		List<ShippingTrackDO> list = shippingTrackService.queryShippingTrack(shippingNo);
		result.setData(list);
		return result.buildIsSuccess(true);
	}
	
	/**
	 * 调用100接口根据返回参数将国内快递运输估计写进info字段
	 * @return
	 */
	@RequestMapping("/insertOrUpdateInfo")
	public void insertOrUpdateInfo() {
		shippingTrackService.insertOrUpdateInfo();
	}
}
