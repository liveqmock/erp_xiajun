package com.wangqin.globalshop.item.api.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemSubOrderService {

	//销量的计算，用在商品管理商品列表，计算已付款的订单和通过erp新建的订单
	@RequestMapping(value = "/subOrder/calItemSalesVolume", method = RequestMethod.POST)
	Integer calItemSalesVolume(@RequestParam("itemCode") String itemCode, @RequestParam("companyNo") String companyNo);
	
//	//首页数据看板：今日GMV（已付款订单金额）
//	List<MallSubOrderDO> sumPaidOrderPriceByDate(Integer dayIndex, String companyNo);
//
//	//首页数据看板：一周GMV（已付款订单数，不含退款订单）
//	List<MallSubOrderDO> sumWeekOrderPrice(String companyNo);
//
//	//首页数据看板：待发货订单数(子订单数)
//	Integer sumWaitSendOrderNum(String companyNo);
}
