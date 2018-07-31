package com.wangqin.globalshop.item.app.controller;


import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.enums.TaskDailyStatus;
import com.wangqin.globalshop.biz1.app.bean.dto.OneWeekSaleDTO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 首页controller
 * @author XiaJun
 * 
 */
@Controller
@Authenticated
@RequestMapping("/home")
public class HomePageController extends BaseController {

//
//	@Autowired
//	private IOuterOrderService outerOrderService;
//	
//	@Autowired
//	private ITaskDailyDetailService iTaskDailyDetailService;
//	
//	@Autowired
//	private ITaskDailyService iTaskDailyService;
	 
//	@Autowired
//	private IInventoryInoutService iInventoryInoutService;
//	
//	@Autowired
//	private ITaskPurchaseService itaskPurchaseService;
//	
//
//	@Autowired
//	private ITaskReceiptService taskReceiptService;
	

	@Autowired
	private IItemService itemService;
	

//	@Autowired
//	private IErpOrderService erpOrderService;
	
	/**
	 * 统一一下下面的接口
	 */
//	@RequestMapping("/todayDatum")
//	@ResponseBody
//	public Object todayDatum() {
//		JsonResult<HomePageDTO> result = new JsonResult<>();
//		Date today = new Date();
//		today.setHours(0);
//		today.setMinutes(0);
//		today.setSeconds(0);
//		//结果集
//		HomePageDTO homePageDTO = new HomePageDTO();
//		//今日订单数
//		Integer todayOrderNum = outerOrderService.countTodayOrders(today);		
//		homePageDTO.setTodayOrderNum(todayOrderNum);
//		//待分配订单数
//		homePageDTO.setWaitForAlloOrderNum(0);
//		//采购商品数
//		Integer purItemNum = iTaskDailyDetailService.countUnPurchasedItemNumber();	
//		homePageDTO.setPurItemNum(purItemNum);
//		//今日入库商品数
//		List<Integer> typeList = new ArrayList<Integer>();
//		typeList.add(InoutOperatorType.PURCHASE_IN.getCode());
//		typeList.add(InoutOperatorType.TRANS_IN.getCode());
//		Integer todayInInvItemNum = iInventoryInoutService.countTodayInInventoryItemNum(typeList, today);
//		homePageDTO.setTodayInInvItemNum(todayInInvItemNum);
//		//采购异常订单数
//		Integer purExcOrderNum = iTaskDailyDetailService.countPurExcOrderNum();
//		homePageDTO.setPurExcOrderNum(purExcOrderNum);
//		//库存异常的商品数
//		homePageDTO.setInvExcItemNum(0);
//		//今日采购订单数
//		List<Integer> statusList = new ArrayList<Integer>();
//		statusList.add(TaskDailyStatus.INIT.getCode());
//		statusList.add(TaskDailyStatus.PURCHASING.getCode());
//		Integer todayPurOrderNum = iTaskDailyService.countTaskStartedUnPurchased(statusList);
//		homePageDTO.setTodayPurOrderNum(todayPurOrderNum);
//		//今日已采购商品数
//		Integer todayPurchasedItemNum = itaskPurchaseService.todayPurchasedItemNumber(today);
//		homePageDTO.setTodayPurchasedItemNum(todayPurchasedItemNum);
//		//预入报商品（即入库）
//		Integer prepareInInvItemNum = taskReceiptService.sumTodayBalanceItemNumber(today);
//		homePageDTO.setPrepareInInvItemNum(prepareInInvItemNum);
//		//已入库商品(即今日入库商品数)
//		//采购中订单数
//		List<Integer> statusListOfPurchasing = new ArrayList<>();
//		statusListOfPurchasing.add(TaskDailyStatus.PURCHASING.getCode());
//		Integer purchasingItemNum = iTaskDailyService.countTaskNumByStatus(statusListOfPurchasing);
//		homePageDTO.setPurchasingItemNum(purchasingItemNum);
//		//待分配订单数(上面有)
//		//已结算商品数(按照预入报商品处理)
//		homePageDTO.setBalancedItemNum(prepareInInvItemNum);
//		//入库商品数
//		List<Integer> typeListInInv = new ArrayList<Integer>();
//		typeListInInv.add(InoutOperatorType.PURCHASE_IN.getCode());
//		typeListInInv.add(InoutOperatorType.TRANS_IN.getCode());
//		Integer inInvItemNum = iInventoryInoutService.countTodayInInventoryItemNum(typeListInInv, null);
//		homePageDTO.setInInvItemNum(inInvItemNum);
//		//采购缺货商品数
//		homePageDTO.setPurProblemItemNum(0);
//		//已发货订单数
//		List<Integer> statusListSent = new ArrayList<Integer>();
//		statusListSent.add(OrderStatus.PART_SENT.getCode());
//		statusListSent.add(OrderStatus.SENT.getCode());	
//		Integer sentOrderNum = outerOrderService.queryOuterOrderByStatus(statusListSent, today).size();
//		homePageDTO.setSentOrderNum(sentOrderNum);
//		//返回
//		result.buildData(homePageDTO);
//		result.buildIsSuccess(true);
//		result.buildMsg("获取数据成功");
//		return result;
//	}
	
	/**
	 * 今日订单数
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/todayOrderNum")
	@ResponseBody
	public Object todayOrdersNumber() {
		JsonResult<Integer> result = new JsonResult<>();
		Date today = new Date();
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
		//Integer todayOrderNumber = outerOrderService.countTodayOrders(today);		
		//result.setData(todayOrderNumber);		
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 今日未分配订单数
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/todayUnAlloOrderNum")
	@ResponseBody
	public Object todayUnAllocatedOrdersNumber() {
		JsonResult<Integer> result = new JsonResult<>();		
		result.setData(0);		//需求不明确，暂时这样
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 待采购商品数
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/unPurItemNum")
	@ResponseBody
	public Object unPurchasedItemNumber() {
		JsonResult<Integer> result = new JsonResult<>();
		//Integer unNumber = iTaskDailyDetailService.countUnPurchasedItemNumber();	
		//result.setData(unNumber);		
		result.setData(0);		
		result.buildIsSuccess(true);	
		return result;
	}
	
	
	/**
	 * 今日入库商品数
	 */
	@RequestMapping("/todayInItemNum")
	@ResponseBody
	public Object todayInInventoryItemNum() {
		JsonResult<Integer> result = new JsonResult<>();
//		Date today = new Date();
//		today.setHours(0);
//		today.setMinutes(0);
//		today.setSeconds(0);
		Calendar calendarInstance = Calendar.getInstance();
		calendarInstance.set(Calendar.HOUR,0);
		calendarInstance.set(Calendar.MINUTE,0);
		calendarInstance.set(Calendar.SECOND,0);
		Date today=calendarInstance.getTime();


		List<Integer> typeList = new ArrayList<Integer>();
		typeList.add(InoutOperatorType.PURCHASE_IN.getCode());
		typeList.add(InoutOperatorType.TRANS_IN.getCode());
//		Integer inNumber = iInventoryInoutService.countTodayInInventoryItemNum(typeList, today);
//		result.setData(inNumber);	
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 采购异常订单数，暂时理解为某个采购任务的需要商品数量!=入库的商品数量，却被标记为采购完成
	 */
	@RequestMapping("/purExcOrderNum")
	@ResponseBody
	public Object purchaseExceptionOrderNumber() {
		JsonResult<Integer> result = new JsonResult<>();
//		Integer exceptionNum = iTaskDailyDetailService.countPurExcOrderNum();
//		result.setData(exceptionNum);		
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 库存异常商品数
	 */
	@RequestMapping("/invExcOrderNum")
	@ResponseBody
	public Object inventoryExceptionOrderNumber() {
		JsonResult<Integer> result = new JsonResult<>();		
		result.setData(0);		//需求不明确，暂时这样
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 今日采购订单数：今日当前时段需要进行采购的订单数
	 * task_daily任务已经开始状态还在待采购和采购中的订单数
	 */
	@RequestMapping("/todayPurOrderNum")
	@ResponseBody
	public Object todayPurchaseOrderNumber() {
		JsonResult<Integer> result = new JsonResult<>();
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(TaskDailyStatus.INIT.getCode());
		statusList.add(TaskDailyStatus.PURCHASING.getCode());
//		Integer purNum = iTaskDailyService.countTaskStartedUnPurchased(statusList);
//		result.setData(purNum);	
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	

	/**
	 * 今日已采购商品数
	 * 
	 */
	@RequestMapping("/todayPurItemNum")
	@ResponseBody
	public Object todayPurchasedItemNumber() {
		JsonResult<Integer> result = new JsonResult<>();
		Date today = new Date();
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
//		Integer purchasedNum = itaskPurchaseService.todayPurchasedItemNumber(today);
//		result.setData(purchasedNum);	
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 预人报商品
	 * 
	 */
	@RequestMapping("/balancedItemNum")
	@ResponseBody
	public Object balancedItemNumber() {
		JsonResult<Integer> result = new JsonResult<>();
		Date today = new Date();
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
//		Integer balancedNum = taskReceiptService.sumTodayBalanceItemNumber(today);
//		result.setData(balancedNum);	
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 采购中的订单
	 * 
	 */
	@RequestMapping("/purchasingOrderNum")
	@ResponseBody
	public Object purchasingOrderNumber() {
		JsonResult<Integer> result = new JsonResult<>();
		List<Integer> statusList = new ArrayList<>();
		statusList.add(TaskDailyStatus.PURCHASING.getCode());
//		Integer purchasingTaskNum = iTaskDailyService.countTaskNumByStatus(statusList);
//		result.setData(purchasingTaskNum);		
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 待分配订单数：还未进行分配的需采购订单
	 * 解释：用户下单后，该商品对应的库存不够，视为未进行分配的需采购订单
	 */
	@RequestMapping("/waitAlloOrderNum")
	@ResponseBody
	public Object waitForAllocateOrderNumber() {
		JsonResult<Integer> result = new JsonResult<>();
		Integer orderNum = 0;
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderStatus.PAID.getCode());
		//获取所有处于已付款待发货的订单
//		List<Long> orderIdList= outerOrderService.queryOuterOrderByStatus(statusList,null);
//		for(Long id:orderIdList) {
//			List<Long> resultList = outerOrderService.waitAllocateOrderNum(id);
//			//其中有一件商品库存不够，视为未进行分配的订单
//			for(Long dis:resultList) {
//				if(dis < 0) {
//					orderNum++;
//					break;
//				}
//			}
//		}
//		result.setData(orderNum);	
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	

	/**
	 * 入库商品数，和上面的今日入库商品数的区别是没有时间限制
	 */
	@RequestMapping("/inItemNum")
	@ResponseBody
	public Object inInventoryItemNum() {
		JsonResult<Integer> result = new JsonResult<>();
		List<Integer> typeList = new ArrayList<Integer>();
		typeList.add(InoutOperatorType.PURCHASE_IN.getCode());
		typeList.add(InoutOperatorType.TRANS_IN.getCode());
//		Integer inNumber = iInventoryInoutService.countTodayInInventoryItemNum(typeList, null);
//		result.setData(inNumber);	
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 采购缺货商品数，这个做不了，暂时没有相关的表和字段
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/purchaseProblem")
	@ResponseBody
	public Object purchaseProblem() {
		JsonResult<Integer> result = new JsonResult<>();		
		result.setData(0);		//需求不明确，暂时这样
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 已发货订单数：今日已经进入发货的订单数
	 * 输出部分发货和全部发货两种状态的订单数
	 * @param
	 * @return
	 */
	@RequestMapping("/onWayOrder")
	@ResponseBody
	public Object onWayOrder() {
		JsonResult<Integer> result = new JsonResult<>();	
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderStatus.PART_SENT.getCode());
		statusList.add(OrderStatus.SENT.getCode());	
		Date today = new Date();
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
//		Integer onOrder = outerOrderService.queryOuterOrderByStatus(statusList, today).size();
//		result.setData(onOrder);	
		result.setData(0);
		result.buildIsSuccess(true);	
		return result;
	}
	
	
	/**
	 * 返回一周销售数据
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/weekSales")
	@ResponseBody
	public Object weekSales() {
		JsonResult<OneWeekSaleDTO> result = new JsonResult<>();	
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderStatus.PAID.getCode());
		statusList.add(OrderStatus.PART_SENT.getCode());
		statusList.add(OrderStatus.SENT.getCode());	
		statusList.add(OrderStatus.SUCCESS.getCode());
        Integer[] salesQuantity = new Integer[7];
		Float salesMoneyTotal = 0.0f;
		Integer salesQuantityTotal = 0;
//		for(int i = 0;i < 7;i++) {
//			salesQuantity[i] = outerOrderService.sumSalesQuantityByDate(statusList, i);
//			salesMoneyTotal += outerOrderService.sumSalesMoneyByDate(statusList,i);
//			salesQuantityTotal += salesQuantity[i];
//		}
//		OneWeekSaleDTO sales = new OneWeekSaleDTO(salesQuantity[0],salesQuantity[1],
//				salesQuantity[2],salesQuantity[3],salesQuantity[4],salesQuantity[5],salesQuantity[6],
//				salesQuantityTotal,salesMoneyTotal);
		OneWeekSaleDTO sales = new OneWeekSaleDTO(0, 0, 0, 0, 0, 0, 0,0,0.0f);
		result.buildData(sales);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 返回七个月销售数据（本月也算）
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/monthSales")
	@ResponseBody
	public Object monthSales() {
		JsonResult<OneWeekSaleDTO> result = new JsonResult<>();	
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderStatus.PAID.getCode());
		statusList.add(OrderStatus.PART_SENT.getCode());
		statusList.add(OrderStatus.SENT.getCode());	
		statusList.add(OrderStatus.SUCCESS.getCode());
        Integer[] salesQuantity = new Integer[7];
		Float salesMoneyTotal = 0.0f;
		Integer salesQuantityTotal = 0;
//		for(int i = 0;i < 7;i++) {
//			salesQuantity[i] = outerOrderService.sumSalesQuantityByMonth(statusList, i);
//			salesMoneyTotal += outerOrderService.sumSalesMoneyByMonth(statusList, i);
//			salesQuantityTotal += salesQuantity[i];
//		}
//		OneWeekSaleDTO sales = new OneWeekSaleDTO(salesQuantity[0],salesQuantity[1],
//				salesQuantity[2],salesQuantity[3],salesQuantity[4],salesQuantity[5],salesQuantity[6],
//				salesQuantityTotal,salesMoneyTotal);
		OneWeekSaleDTO sales = new OneWeekSaleDTO(0, 0, 0, 0, 0, 0, 0,0,0.0f);
		result.buildData(sales);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 按天上新量
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/weekNewItem")
	@ResponseBody
	public Object weekNewItem() {
		JsonResult<Integer[]> result = new JsonResult<>();
        Integer[] newItemNum = new Integer[7];
		for(int i = 0;i < 7;i++) {
			newItemNum[i] = itemService.sumNewItemNumByDate(i);
		}
		result.buildData(newItemNum);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 按月上新量
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/monthNewItem")
	@ResponseBody
	public Object monthNewItem() {
		JsonResult<Integer[]> result = new JsonResult<>();
        Integer[] newItemNum = new Integer[7];
		for(int i = 0;i < 7;i++) {
			newItemNum[i] = itemService.sumNewItemNumByMonth(i);
		}
		result.buildData(newItemNum);
		result.buildIsSuccess(true);	
		return result;
	}
	
	/**
	 * 今日发货订单列表
	 * 理解为今天部分发货+全部发货的erp_order表
	 * @param
	 * @return
	 */
	@RequestMapping("/todaySendOrder")
	@ResponseBody
	public Object todaySendOrder() {
		JsonResult<String> result = new JsonResult<>();	
//		List<Integer> statusList = new ArrayList<>();
//		statusList.add(OrderStatus.SENT.getCode());
//		statusList.add(OrderStatus.PART_SENT.getCode());
//		List<ErpOrder> orderList = erpOrderService.todaySendOrders(statusList);
//		if(0 == orderList.size()) {
//			result.buildIsSuccess(false);
//			result.buildMsg("今日无发货订单");
//		} else {
//			result.buildData(orderList);
//			result.buildIsSuccess(true);	
//		}
		result.buildIsSuccess(true);
		//result.buildMsg("");
		return result;
	}
	
}
