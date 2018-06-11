//package com.wangqin.globalshop.purchase.service.impl;
//
//import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;
//import com.wangqin.globalshop.biz1.app.dal.dataVo.ReceiptQueryVO;
//import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerReceiptDOMapper;
//import com.wangqin.globalshop.common.utils.JsonPageResult;
//import com.wangqin.globalshop.purchase.service.IReceiptService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ReceiptServiceImpl //extends SuperServiceImpl<ReceiptMapper, BuyerReceiptDO>
//		implements IReceiptService {
//
//	@Autowired
//	BuyerReceiptDOMapper buyerReceiptDOMapper;
//
//	@Override
//	public JsonPageResult<List<BuyerReceiptDO>> queryReceipt(ReceiptQueryVO receiptQueryVO) {
//		JsonPageResult<List<BuyerReceiptDO>> receiptResult = new JsonPageResult<>();
//		//1、查询总的记录数量
//		Integer totalCount =  buyerReceiptDOMapper.queryReceiptCount(receiptQueryVO);
//
//		//2、查询分页记录
//		if(totalCount!=null&&totalCount!=0){
//			receiptResult.buildPage(totalCount, receiptQueryVO);
//			List<BuyerReceiptDO> receipts = buyerReceiptDOMapper.queryReceipt(receiptQueryVO);
//			receiptResult.setData(receipts);
//		}else{
//			List<BuyerReceiptDO> receipts  = new ArrayList<>();
//			receiptResult.setData(receipts);
//		}
//		return receiptResult;
//	}
//
//	/*@Autowired
//	private TaskReceiptMapper taskReceiptMapper;
//
//	@Override
//	public void createReceipt(BuyerReceiptDO receipt) {
//
//		this.baseMapper.insert(receipt);
//		 List<TaskReceipt>  taskReceipts = receipt.getTaskReceiptList();
//		 taskReceipts.forEach(
//				 taskReceipt->taskReceipt.setReceiptId(receipt.getId())
//				 );
//		 taskReceiptMapper.insertBatch(taskReceipts);
//	}*/
//
//
//}
