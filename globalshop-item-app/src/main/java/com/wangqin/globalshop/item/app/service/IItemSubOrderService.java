package com.wangqin.globalshop.item.app.service;



public interface IItemSubOrderService {

	//销量的计算，用在商品管理商品列表，计算已付款的订单和通过erp新建的订单
	Integer calItemSalesVolume(String itemCode,String companyNo);
}
