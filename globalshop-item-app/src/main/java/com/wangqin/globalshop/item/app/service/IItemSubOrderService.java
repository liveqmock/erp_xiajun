package com.wangqin.globalshop.item.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;



public interface IItemSubOrderService {

	//销量的计算，用在商品管理商品列表，计算已付款的订单和通过erp新建的订单
	Integer calItemSalesVolume(String itemCode,String companyNo);
	
	//首页数据看板：今日GMV（已付款订单金额）
	List<MallSubOrderDO> sumPaidOrderPriceByDate(Integer dayIndex,String companyNo);
		
	//首页数据看板：一周GMV（已付款订单数，不含退款订单）
	List<MallSubOrderDO> sumWeekOrderPrice (String companyNo);
	
	//首页数据看板：待发货订单数(子订单数)
	Integer sumWaitSendOrderNum(String companyNo);
}
