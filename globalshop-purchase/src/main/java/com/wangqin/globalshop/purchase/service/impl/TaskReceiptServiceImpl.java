//package com.wangqin.globalshop.purchase.service.impl;
//
//import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;
//import com.wangqin.globalshop.biz1.app.dal.dataVo.TaskReceiptQueryVO;
//import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerReceiptDOMapperExt;
//import com.wangqin.globalshop.common.utils.HaiJsonUtils;
//import com.wangqin.globalshop.common.utils.JsonPageResult;
//import com.wangqin.globalshop.common.utils.PicModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class TaskReceiptServiceImpl //extends SuperServiceImpl<TaskReceiptMapper, BuyerReceiptDO>
//		implements ITaskReceiptService {
//	@Autowired
//	BuyerReceiptDOMapperExt buyerReceiptDOMapper;
//
//	@Override
//	public JsonPageResult<List<BuyerReceiptDO>> queryTaskReceipt(TaskReceiptQueryVO taskReceiptQueryVO) {
//		JsonPageResult<List<BuyerReceiptDO>> taskReceiptResult = new JsonPageResult<>();
//		//1、查询总的记录数量
//		Integer totalCount =  buyerReceiptDOMapper.queryTaskReceiptCount(taskReceiptQueryVO);
//
//		//2、查询分页记录
//		if(totalCount!=null&&totalCount!=0){
//			taskReceiptResult.buildPage(totalCount, taskReceiptQueryVO);
//			List<BuyerReceiptDO> taskReceipts = buyerReceiptDOMapper.queryTaskReceipt(taskReceiptQueryVO);
//			List<BuyerReceiptDO> newTaskReceipts = new ArrayList<>();
//			for (BuyerReceiptDO taskReceipt : taskReceipts) {
//				PicModel pm = HaiJsonUtils.toBean(taskReceipt.getSkuPic(), PicModel.class);
//    			String imgSrc = pm.getPicList().get(0).getUrl();
//				taskReceipt.setSkuPic(imgSrc);
//				newTaskReceipts.add(taskReceipt);
//			}
//			taskReceiptResult.setData(newTaskReceipts);
//		}else{
//			List<BuyerReceiptDO> newTaskReceipts  = new ArrayList<>();
//			taskReceiptResult.setData(newTaskReceipts);
//		}
//		return taskReceiptResult;
//	}
//
//	/*@Autowired
//	private TaskReceiptMapper taskReceiptMapper;
//
//	@Override
//	public void createReceipt(Receipt receipt) {
//
//		this.baseMapper.insert(receipt);
//		 List<BuyerReceiptDO>  taskReceipts = receipt.getTaskReceiptList();
//		 taskReceipts.forEach(
//				 taskReceipt->taskReceipt.setReceiptId(receipt.getId())
//				 );
//		 taskReceiptMapper.insertBatch(taskReceipts);
//	}*/
//
//	/**
//	 * 计算task_daily_detail对应的多个小票的quantity和trans_quantity的和，即是否结算
//	 * @author XiaJun
//	 * @return
//	 */
//	@Override
//	public Integer sumQuantityByTaskDailyId(Long id) {
//		return buyerReceiptDOMapper.sumQuantityByTaskDailyId(id);
//	}
//
//	@Override
//	public Integer sumTodayBalanceItemNumber(Date today) {
//		return buyerReceiptDOMapper.sumTodayBalanceItemNumber(today);
//	}
//
//}
