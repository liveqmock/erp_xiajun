package com.wangqin.globalshop.schedule.job;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.common.enums.ItemIsSale;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.schedule.service.ScheduleItemService;

/**
 * 扫描商品表，将销售结束的商品标为不可售
 * update item.is_sale
 * per 10m
 * @author xiajun
 *
 */
@Component
public class ItemSalableJob {

	 @Autowired
	 private ScheduleItemService scheduleItemService;
	 
	 @Autowired
	 private TransactionTemplate transactionTemplate;
	 
//商品已经没有销售结束时间的概念，下架的定时任务去掉
//	 @Scheduled(cron = "* 0/10 * * * ? ")
//	 public void updateItemSalable() {
//		 //System.out.println("scheduled job-->start to scan item sale state");
//		 List<ItemDO> itemList = scheduleItemService.queryItemSalable(ItemIsSale.SALABLE.getCode().byteValue());
//		 if(IsEmptyUtil.isCollectionNotEmpty(itemList)) {
//			 for(ItemDO item:itemList) {
//				 Date startDate = item.getStartDate();
//				 Date endDate = item.getEndDate();
//				 if (!DateUtil.belongCalendar(new Date(), startDate, endDate)) {
//					 scheduleItemService.updateItemIsSale(ItemIsSale.UNSALABLE.getCode().byteValue(), item.getItemCode());
//					 scheduleItemService.putOffItemByItemCode(item.getItemCode());
//				 }
//			 }
//		 }
//		//System.out.println("scheduled job-->end to scan item sale state");
//	 }
	 
	 /**
	  * 自定义上架时间上架
	  */
	 @Scheduled(cron = "* 0/10 * * * ? ")
	 public void updateItemSalable() {
		 //System.out.println("scheduled job-->start to scan item sale state");
		 List<ItemDO> itemList = scheduleItemService.queryItemSelfDefineTime();
		 if (IsEmptyUtil.isCollectionNotEmpty(itemList)) {
			 for (ItemDO item:itemList) {
				 Date startDate = item.getStartDate();
				 if (null != startDate) {
					 if (!DateUtil.afterCalendar(startDate,new Date())) {
						 boolean result = transactionTemplate.execute(new TransactionCallback<Boolean>() {
							 @Override
							 public Boolean doInTransaction(TransactionStatus transactionStatus) {
								 scheduleItemService.updateItemIsSaleAndStatus(item.getItemCode());
								 return Boolean.TRUE;
						     }
						});
					 }
				 }
			 }
		 }
		//System.out.println("scheduled job-->end to scan item sale state");
	 }
}
