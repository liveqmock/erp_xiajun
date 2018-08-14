package com.wangqin.globalshop.item.app.controller;


import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;

import com.wangqin.globalshop.biz1.app.bean.dataVo.HomePageVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.AppUtil;

import com.wangqin.globalshop.item.app.service.IItemMallOrderService;
import com.wangqin.globalshop.item.app.service.IItemShippingOrderService;
import com.wangqin.globalshop.item.app.service.IItemSubOrderService;

/**
 * 首页controller
 * @author XiaJun
 * 
 */
@Controller
@Authenticated
@RequestMapping("/home")
public class HomePageController extends BaseController {
	

	@Autowired
	private IItemMallOrderService orderService;
	@Autowired
	private IItemSubOrderService subOrderService;
	@Autowired
	private IItemShippingOrderService shippingService;

	
	/**
	 * 首页数据看板
	 */
	@RequestMapping("/data")
	@ResponseBody
	public Object todayDatum() {
		JsonResult<HomePageVO> result = new JsonResult<HomePageVO>();
		String companyNo = AppUtil.getLoginUserCompanyNo();
		HomePageVO hpVO = new HomePageVO();
		//今日订单数
		Integer todayOrderNum = orderService.sumPaidOrderNumByDate(0, companyNo);
		hpVO.setTodayOrderNum(12);
		//今日gmv
		Double todayGmv = subOrderService.sumPaidOrderPriceByDate(0, companyNo);
		hpVO.setTodayGmv(13.00);
		//一周订单数
		Integer weekOrderNum = 0;
		for (int i = 0;i < 7;i++) {
			weekOrderNum += orderService.sumPaidOrderNumByDate(i, companyNo);
		}
		hpVO.setWeekOrderNum(14);
		//一周gmv
		Double weekGmv = subOrderService.sumWeekOrderPrice(companyNo);
		hpVO.setWeekGmv(15.00);
		//待发货订单数
		Integer waitSendOrderNo = orderService.sumWaitSendOrderNum(companyNo);
		hpVO.setWaitSendOrderNum(16);
		//今日发货包裹数
		Integer todyNum = shippingService.sumTodySentNum(companyNo);
		hpVO.setTodaySendNum(17);
		//一周发货包裹数量
		Integer weekNum = shippingService.sumWeekSentNum(companyNo);
		hpVO.setWeekSendNum(18);
		//未完成售后订单数
		Integer returningOrderNum = orderService.sumReturningOrderNum(companyNo);
		hpVO.setReturningOrderNum(19);
		//一周销量统计
		List<Integer> dateList = pastSevenDay();
		hpVO.setFirstDay(dateList.get(0));
		hpVO.setSecondDay(dateList.get(1));
		hpVO.setThirdDay(dateList.get(2));
		hpVO.setFourthDay(dateList.get(3));
		hpVO.setFifthDay(dateList.get(4));
		hpVO.setSixthDay(dateList.get(5));
		hpVO.setSeventhDay(dateList.get(6));		
		Double salesVolume0 = subOrderService.sumPaidOrderPriceByDate(0, companyNo);
		hpVO.setFirstSales(20.01);
		Double salesVolume1 = subOrderService.sumPaidOrderPriceByDate(1, companyNo);
		hpVO.setSecondSales(21.01);
		Double salesVolume2 = subOrderService.sumPaidOrderPriceByDate(2, companyNo);
		hpVO.setThirdSales(22.01);
		Double salesVolume3 = subOrderService.sumPaidOrderPriceByDate(3, companyNo);
		hpVO.setFourthSales(23.01);
		Double salesVolume4 = subOrderService.sumPaidOrderPriceByDate(4, companyNo);
		hpVO.setFifthSales(24.01);
		Double salesVolume5 = subOrderService.sumPaidOrderPriceByDate(5, companyNo);
		hpVO.setSixthSales(25.01);
		Double salesVolume6 = subOrderService.sumPaidOrderPriceByDate(6, companyNo);
		hpVO.setSeventhSales(26.01);
		
		result.buildData(hpVO);
		return result.buildIsSuccess(true).buildMsg("查找成功");
		
	}
	
	//计算过去7天的日期
	public static List<Integer> pastSevenDay() {
		List<Integer> dateList = new ArrayList<Integer>();		
		Calendar cal = Calendar.getInstance();
		dateList.add(cal.get(Calendar.DAY_OF_MONTH));
		Integer dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		if (dayOfYear >= 7) { //在当前年份内计算
			for (int i = 0;i < 6;i++) {
				cal.set(Calendar.DAY_OF_YEAR, --dayOfYear);
				dateList.add(cal.get(Calendar.DAY_OF_MONTH));
			}
		} else { //需要往前一年推算
			while (dayOfYear >= 1) {
				dateList.add(dayOfYear--);
			}
			int j = 0;
			while (dateList.size() < 7) {
				dateList.add(31-j);
			}
		}
		return dateList;
	}
	

	
}
