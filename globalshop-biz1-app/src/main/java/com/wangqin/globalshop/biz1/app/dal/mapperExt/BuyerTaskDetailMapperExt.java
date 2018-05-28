package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerTaskDetailDOMapper;


/**
 * @author zhulu
 *
 */
public interface BuyerTaskDetailMapperExt extends BuyerTaskDetailDOMapper{

	//List<BuyerTaskDailyDTO> queryBuyerTaskList(BuyerTaskVO buyerTaskVO);
	
	//List<ItemSku> taskDailyExportByTaskId(Long taskId);
	
	//List<TaskDailyDetail> queryDailyDetailByTaskId(Long taskId);
	
	//void closeTaskDetailDaily(@Param("taskDailyIdList")List<Long> taskDailyIdList);
	
	//void finishTaskDetailDaily(@Param("taskDailyIdList")List<Long> taskDailyIdList);
	
	//void updateTaskDetailDailyStatus(@Param("status")Integer status,@Param("taskDailyIdList")List<Long> taskDailyIdList);
	
	void updateUpcForTaskDailyDetail(MallOrderDO erpOrder);
	
	/**s
	 * 查找一个task_deily对应的多个task_deilay_detail，XiaJun
	 * @author XiaJun
	 */
	//List<Long> queryTaskDailyDetailsByTaskDailyId(Long id);
	
	/**
	 * 判断该商品是否已经入库，count=in_incount表示已入库
	 * @param task_daily_detail_id
	 * @author XiaJun
	 */
	//Integer judgeItemIn(Long task_daily_detail_id);
	
	/**
	 * 待采购商品数
	 * @param task_daily_detail_id
	 * @author XiaJun
	 */
	//Integer countUnPurchasedItemNumber();
	
	/**
	 * 采购异常订单数
	 * @return
	 */
	//Integer countPurExcOrderNum();
}
