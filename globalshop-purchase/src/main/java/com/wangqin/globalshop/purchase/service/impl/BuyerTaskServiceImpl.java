package com.wangqin.globalshop.purchase.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.TaskPurchaseVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerTaskDOMapperExt;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.purchase.service.IBuyerTaskService;
import com.wangqin.globalshop.purchase.service.IReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class BuyerTaskServiceImpl //extends SuperServiceImpl<TaskPurchaseMapper, BuyerTaskDO>
		implements IBuyerTaskService {


	@Autowired
	private BuyerTaskDOMapperExt buyerTaskDOMapper;
	@Autowired
	private IReceiptService receiptService;
	
	@Override
	public List<BuyerTaskDO> queryTaskPurchaseBySubTask(Long subTaskId) {
//		 EntityWrapper<BuyerTaskDO> entityWrapper = new EntityWrapper<>();
//		 //未被关闭子任务的采购任务流水
//		entityWrapper.where("sub_task_id={0} and status != {1}", subTaskId, GeneralStatus.CLOSE.getCode());
//		return buyerTaskDOMapper.selectList(entityWrapper);
		//TODO
		return null;
	}
	
	
	@Override
	public List<BuyerTaskDO> queryTaskPurchaseList(Long buyerId,Integer status) {
//		 EntityWrapper<BuyerTaskDO> entityWrapper = new EntityWrapper<>(); 
		 //该子任务没有被关闭的采购任务流水
		 String todayStr =  DateUtil.formatDate(new Date(), DateUtil.SIMPLE_DATE_PARTEN);
		 Date dateBegin = null;
		 Date dateEnd = null;
		 try {
			 dateBegin = DateUtil.convertStr2Date(todayStr,DateUtil.SIMPLE_DATE_PARTEN);
			 dateEnd = DateUtil.convertStr2Date(todayStr+" 23:59:59",DateUtil.DATE_PARTEN);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 TaskPurchaseVO tpVO = new TaskPurchaseVO();
		 tpVO.setBuyerId(buyerId);
		 tpVO.setStatus(status);
		 tpVO.setStartTime(dateBegin);
		 tpVO.setEndTime(dateEnd);
//		entityWrapper.where("buyer_id={0} and status = {1} and gmt_create>{2} and gmt_create<{3}", buyerId,status,dateBegin,dateEnd);
//		 entityWrapper.where("buyer_id={0} and status = {1}", buyerId,status);
		return buyerTaskDOMapper.queryTaskPurchaseList(tpVO);
	}


	@Override
	public void createReceipt(BuyerReceiptDO receipt, List<BuyerTaskDO> tps) {
		if(receipt!=null){
//			receiptService.insert(receipt);
			tps.forEach(tp->{
//				tp.setStatus(GeneralStatus.CLOSE.getCode());
//				tp.setReceiptId(receipt.getId());
				tp.setGmtModify(new Date());

			});
			buyerTaskDOMapper.updateBatchById(tps);

		}else{
			throw new RuntimeException("没有小票");
		}

	}
	
	@Override
	public Integer todayPurchasedItemNumber(Date today) {
		return buyerTaskDOMapper.todayPurchasedItemNumber(today);
	}

}
