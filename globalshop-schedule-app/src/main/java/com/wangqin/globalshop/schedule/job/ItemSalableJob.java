package com.wangqin.globalshop.schedule.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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

	 private static Logger logger = LoggerFactory.getLogger("ItemSalableJob");
	 
	 /**
	  * 自定义上架时间上架
	  */
	 @Scheduled(cron = "35 0/10 * * * ? ")
	 public void updateItemSalable() {
		 logger.info("==>item put on task");			 
		 boolean result = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			 @Override
			 public Boolean doInTransaction(TransactionStatus transactionStatus) {
				 scheduleItemService.putOnItemSelfDefineTime();
				 return Boolean.TRUE;
			 }
		 });
	 }
}
