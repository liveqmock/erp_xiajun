package com.wangqin.globalshop.order.app.controller.shipping;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingTrackDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.order.app.kuaidi_bean.Kuaidi100ShippingTrackResultNode;
import com.wangqin.globalshop.order.app.kuaidi_bean.Kuaidi100ShippingTrackResult;
import com.wangqin.globalshop.order.app.service.shipping.IShippingTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 777 on 2018/6/6
 *
 * 海狐调用，查询接口，暂不进行签名校验，也不进行内部系统权限校验
 */

@Controller
@RequestMapping("/haihu")
public class HaihuQueryShipTrackComtroller {

	@Autowired
	private IShippingTrackService shippingTrackService;


	@RequestMapping("/queryByLogisticNo")
	@ResponseBody
	public Object queryShippingTrack(String logisticNo) {
		JsonResult<Kuaidi100ShippingTrackResult> response = new JsonResult<>();
		List<ShippingTrackDO> list = shippingTrackService.selectShippingTrackListByLogisticNo(logisticNo);

		Kuaidi100ShippingTrackResult result = new Kuaidi100ShippingTrackResult();

		ArrayList<Kuaidi100ShippingTrackResultNode> itemlist = new ArrayList<>();

		result.setStatus("成功");
		for(ShippingTrackDO shippingTrackDO : list){
			Kuaidi100ShippingTrackResultNode item = new Kuaidi100ShippingTrackResultNode();
			item.setContext(shippingTrackDO.getTrackInfo());
			item.setTime(DateUtil.convertDate2Str(shippingTrackDO.getGmtCreate(),DateUtil.formateStr19));
			item.setFtime(DateUtil.convertDate2Str(shippingTrackDO.getGmtModify(),DateUtil.formateStr19));
			itemlist.add(item);
		}
		result.setData(itemlist);
		response.setData(result);
		return response.buildIsSuccess(true);
	}
}
