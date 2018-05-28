package com.wangqin.globalshop.biz1.app.task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.wangqin.enums.GeneralStatus;
import com.wangqin.model.InventoryListener;
import com.wangqin.service.IInventoryListenerService;

/**
 * 根据数据库监听同步库存到有赞
 * @author 朱路
 *
 */
@Component
public class AutoYouzanInventoryTask {
	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private SynInventoryTask synInventoryTask;
	
	@Autowired
	private IInventoryListenerService inventoryListenerService;
	
	//@Scheduled(cron = "0 0/5 * * * ?")
	public void run(){
		try {
			List<InventoryListener> invents = inventoryListenerService.getInventoryListener();
			if (CollectionUtils.isNotEmpty(invents)) {
				Set<Long> set = new HashSet<Long>();
				List<InventoryListener> updateListener = Lists.newArrayList();
				for (InventoryListener inventoryListener : invents) {
					if (!set.contains(inventoryListener.getSkuId())) {
						set.add(inventoryListener.getSkuId());
						synInventoryTask.synSkuInventoryListener(inventoryListener);
					} else {
						inventoryListener.setStatus(GeneralStatus.SUCCESS.getCode());
						updateListener.add(inventoryListener);
					}
				}
				if (!updateListener.isEmpty()) {
					synInventoryTask.batchUpdateInventoryListener(updateListener);
				}
			}
		} catch (Exception e) {
			logger.info("AutoYouzanInventoryTask error:"+e);
		}
	}
	
}
