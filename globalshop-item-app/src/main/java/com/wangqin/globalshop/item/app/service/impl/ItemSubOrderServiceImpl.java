package com.wangqin.globalshop.item.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.item.app.service.IItemSubOrderService;


@Service
public class ItemSubOrderServiceImpl implements IItemSubOrderService{

	@Autowired
	private MallSubOrderMapperExt mapperExt;
	
	//销量的计算，用在商品管理商品列表，计算已付款的订单和通过erp新建的订单
	@Override
	public Integer calItemSalesVolume(String itemCode,String companyNo) {
		List<Integer> statusList = new ArrayList<Integer>();
		//除了待付款和关闭的订单，其他全部计算
		statusList.add(OrderStatus.NEW.getCode());
		statusList.add(OrderStatus.PAID.getCode());
		statusList.add(OrderStatus.PART_SENT.getCode());
		statusList.add(OrderStatus.SENT.getCode());
		statusList.add(OrderStatus.RETURNING.getCode());
		statusList.add(OrderStatus.RETURNDONE.getCode());
		statusList.add(OrderStatus.SUCCESS.getCode());
		statusList.add(OrderStatus.COMFIRM.getCode());
		return mapperExt.calItemSalesVolume(statusList, itemCode, companyNo);
	}
	
	//首页数据看板：今日GMV（已付款订单金额）
	@Override
	public List<MallSubOrderDO> sumPaidOrderPriceByDate(Integer dayIndex,String companyNo) {
		return mapperExt.sumPaidOrderPriceByDate(OrderStatus.paidList(), dayIndex, companyNo);
	}
		
	//首页数据看板：一周GMV（已付款订单数，不含退款订单）
	@Override
	public List<MallSubOrderDO> sumWeekOrderPrice (String companyNo) {
		return mapperExt.sumWeekOrderPrice(OrderStatus.paidListNotReturn(), companyNo);
	}
	
	//首页数据看板：待发货订单数(子订单数)
	@Override
	public Integer sumWaitSendOrderNum(String companyNo) {
		return mapperExt.sumWaitSendOrderNum(OrderStatus.waitSendList(), companyNo);
	}
		
}
