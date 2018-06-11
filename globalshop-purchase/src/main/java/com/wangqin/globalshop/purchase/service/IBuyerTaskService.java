package com.wangqin.globalshop.purchase.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;

import java.util.Date;
import java.util.List;

public interface IBuyerTaskService {

	/**
	 * 通过子任务ID获取所有采购流水记录
	 * @param subTaskId
	 * @return
	 */
	List<BuyerTaskDO> queryTaskPurchaseBySubTask(Long subTaskId);
	
	
	/**
	 * 查询今天未完成的采购流水记录
	 * @param
	 * @return
	 */
	List<BuyerTaskDO> queryTaskPurchaseList(Long buyerId, Integer status);

	/**
	 * 新增小票记录，更新流水的状态
	 * @param receipt
	 * @param tps
	 */
	void createReceipt(BuyerReceiptDO receipt, List<BuyerTaskDO> tps);
	
	Integer todayPurchasedItemNumber(Date today);
}
