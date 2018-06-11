//package com.wangqin.globalshop.purchase.service.impl;
//
//import com.baomidou.framework.service.impl.SuperServiceImpl;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDetailDO;
//import com.wangqin.globalshop.biz1.app.dal.dataObject.PurchaseStorage;
//import com.wangqin.globalshop.biz1.app.dal.dataObject.PurchaseStorageDetail;
//import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;
//import com.wangqin.globalshop.biz1.app.dal.mapper.*;
//import com.wangqin.globalshop.biz1.app.enums.GeneralStatus;
//import com.wangqin.globalshop.biz1.app.exception.ErpCommonException;
//import com.wangqin.globalshop.biz1.app.service.oldversion.IInventoryAreaService;
//import com.wangqin.globalshop.biz1.app.service.oldversion.ISequenceUtilService;
//import com.wangqin.globalshop.biz1.app.service.oldversion.IPurchaseStorageService;
//import com.wangqin.globalshop.common.utils.ImageUtil;
//import com.wangqin.globalshop.common.utils.ShiroUtil;
//import com.wangqin.globalshop.common.utils.JsonResult;
//import com.wangqin.globalshop.common.base.BaseController;
//import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
//import com.wangqin.globalshop.common.shiro.ShiroUser;
//import com.wangqin.globalshop.common.utils.HaiJsonUtils;
//import com.wangqin.globalshop.biz1.app.vo.PurchaseStorageVO;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class PurchaseStorageServiceImpl // extends SuperServiceImpl<PurchaseStorageMapper, PurchaseStorage>
//		implements IPurchaseStorageService {
//
//	@Autowired
//	private PurchaseStorageDetailMapper purchaseStorageDetailMapper;
//
//	@Autowired
//	private IPurchaseStorageService purchaseStorageService;
//
//	@Autowired
//	private ISequenceUtilService sequenceUtilService;
//
//	@Autowired
//	private ReceiptMapper receiptMapper;
//
//	@Autowired
//	private IInventoryAreaService inventoryAreaService;
//
//	@Autowired
//	private TaskDailyDetailMapper taskDailyDetailMapper;
//
//	@Autowired
//	private ItemSkuMapper itemSkuMapper;
//
//	@Override
//	public void putInStorage(PurchaseStorage ps,List<BuyerReceiptDO> recs) {
//		this.baseMapper.insert(ps);
//		List<PurchaseStorageDetail>  details = ps.getPurchaseStorageDetailList();
//		if(details!=null&&!details.isEmpty()){
//			details.forEach(
//				detail->{
//					detail.setStorageId(ps.getId());
//					detail.setStoOrderNo(ps.getStoOrderNo());
//				}
//			);
//			purchaseStorageDetailMapper.insertBatch(details);
//		}
//		//更新小票状态
//
//		for(BuyerReceiptDO rec:recs){
//			rec.setStatus(GeneralStatus.SUCCESS.getCode());
//			rec.setGmtModify(new Date());
//		}
//		receiptMapper.updateBatchById(recs);
//	}
//
//	@Override
//	public void addPurchaseStorage(PurchaseStorage ps) {
//		this.baseMapper.insert(ps);
//		List<PurchaseStorageDetail> purDetailList =  ps.getPurchaseStorageDetailList();
//		//List<ItemSku> itemSkuList =  new ArrayList<ItemSku>();
//
//		if(CollectionUtils.isNotEmpty(purDetailList)){
//			for(PurchaseStorageDetail psd:purDetailList){
//				psd.setStorageId(ps.getId());
//				psd.setGmtCreate(new Date());
//				psd.setGmtModify(new Date());
//				psd.setStoOrderNo(ps.getStoOrderNo());
//				psd.setWarehouseId(ps.getWarehouseId());
//				psd.setWarehouseName(ps.getWarehouseName());
//
//				/*
//				ItemSku itemSku = new ItemSku();
//				itemSku.setId(psd.getSkuId());
//				itemSku.setPurchasePrice(psd.getPrice());
//				itemSkuList.add(itemSku);
//				*/
//			}
//			purchaseStorageDetailMapper.insertBatch(purDetailList);
//			/*
//			if(!itemSkuList.isEmpty()) {
//				itemSkuMapper.updateBatchById(itemSkuList);
//			}
//			*/
//		}
//	}
//
//	@Override
//	public PurchaseStorage queryPurchaseStorage(Long purStoId) {
//		PurchaseStorage ps = this.baseMapper.selectById(purStoId);
//		if(ps==null){
//			throw new ErpCommonException("没有找到入库单");
//		}else{
////			Map<String,Object> clumnMap = new HashMap<>();
////			clumnMap.put("storage_Id", purStoId);
//			List<PurchaseStorageDetail> psdList = purchaseStorageDetailMapper.queryPurStoDetails(purStoId);
//			if(psdList!=null){
//				if(psdList.size()>0) {
//					int totalQuantity = 0;
//					int totalTransQuantity = 0;
//					int totalTaskDailyCount = 0;
//					int totalIntcount = 0;
//					for(int i=0; i<psdList.size(); i++) {
//						PurchaseStorageDetail psd = psdList.get(i);
//						if(psd.getQuantity() > 0) {
//							totalQuantity += psd.getQuantity();
//						}
//						if(psd.getTransQuantity() > 0) {
//							totalTransQuantity += psd.getTransQuantity();
//						}
//						if(psd.getTaskDailyCount()!=null && psd.getTaskDailyCount()>0) {
//							totalTaskDailyCount += psd.getTaskDailyCount();
//						}
//						if(psd.getInCount()!=null && psd.getInCount()>0) {
//							totalIntcount += psd.getInCount();
//						}
//					}
//					ps.setTotalQuantity(totalQuantity);
//					ps.setTotalTransQuantity(totalTransQuantity);
//					ps.setTotalIntcount(totalIntcount);
//					if(totalTaskDailyCount>0) {
//						ps.setTotalTaskDailyCount(totalTaskDailyCount);
//					}
//				}
//				ps.setPurchaseStorageDetailList(psdList);
//			}
//		}
//		return ps;
//	}
//
//	@Override
//	public void updatePurchaseStorage(PurchaseStorage ps) {
//		PurchaseStorage psDb = queryPurchaseStorage(ps.getId());
//		List<PurchaseStorageDetail> psListDb=psDb.getPurchaseStorageDetailList();
//		this.baseMapper.updateSelectiveById(ps);
//		List<PurchaseStorageDetail> psList = ps.getPurchaseStorageDetailList();
//		List<PurchaseStorageDetail> psListNew = Lists.newArrayList();
//		List<PurchaseStorageDetail> psListUpdate=Lists.newArrayList();
//		//List<ItemSku> itemSkuList =  new ArrayList<ItemSku>();
//
//		Set<Long> all = new HashSet<Long>();
//		if(CollectionUtils.isNotEmpty(psListDb)){
//			psListDb.forEach(psd->{ all.add(psd.getId()); });
//		}
//		psList.forEach(
//			pps->{
//				pps.setStorageId(ps.getId());
//				if(pps.getId()==null){
//					pps.setGmtCreate(new Date());
//					pps.setGmtModify(new Date());
//					pps.setStoOrderNo(ps.getStoOrderNo());
//					pps.setWarehouseId(ps.getWarehouseId());
//					pps.setWarehouseName(ps.getWarehouseName());
//					psListNew.add(pps);
//				}else{
//					if(!all.isEmpty()){
//						all.remove(pps.getId());
//					}
//					pps.setWarehouseId(ps.getWarehouseId());
//					pps.setWarehouseName(ps.getWarehouseName());
//					pps.setGmtModify(new Date());
//					psListUpdate.add(pps);
//				}
//
//				/*
//				ItemSku itemSku = new ItemSku();
//				itemSku.setId(pps.getSkuId());
//				itemSku.setPurchasePrice(pps.getPrice());
//				itemSkuList.add(itemSku);
//				*/
//			  }
//			);
//
//		if(!psListNew.isEmpty()){
//			purchaseStorageDetailMapper.insertBatch(psListNew);
//		}
//
//		if(!psListUpdate.isEmpty()){
//			purchaseStorageDetailMapper.updateBatchById(psListUpdate);
//		}
//
//		if(!all.isEmpty()){
//			purchaseStorageDetailMapper.deleteBatchIds(new ArrayList<Long>(all));
//		}
//		/*
//		if(!itemSkuList.isEmpty()) {
//			itemSkuMapper.updateBatchById(itemSkuList);
//		}
//		*/
//	}
//
//	@Override
//	public void confirmPurchaseStorage(PurchaseStorage ps) {
//		this.updatePurchaseStorage(ps);
//		updateTaskDailyDetailInCount(ps);
//		//入库
//		inventoryAreaService.importPurchaseStorage(ps);
//	}
//
//	@Override
//	public JsonPageResult<List<PurchaseStorage>> queryPurStorages(PurchaseStorageVO psVO) {
//		JsonPageResult<List<PurchaseStorage>> purchaseStorageResult = new JsonPageResult<>();
//		//1、查询总的记录数量
//		Integer totalCount =  this.baseMapper.queryPurStoragesCount(psVO);
//
//		//2、查询分页记录
//		if(totalCount!=null&&totalCount!=0){
//			purchaseStorageResult.buildPage(totalCount, psVO);
//			List<PurchaseStorage> pslist = this.baseMapper.queryPurStorages(psVO);
//			purchaseStorageResult.setData(pslist);
//		}else{
//			List<PurchaseStorage> psList  = new ArrayList<>();
//			purchaseStorageResult.setData(psList);
//		}
//		return purchaseStorageResult.buildIsSuccess(true);
//	}
//
//	@Override
//	public void addConfirmPurchaseStorage(PurchaseStorage ps) {
//		this.addPurchaseStorage(ps);
//		updateTaskDailyDetailInCount(ps);
//		//入库
//		inventoryAreaService.importPurchaseStorage(ps);
//	}
//
//	@Override
//	public void deletePurchaseStorage(PurchaseStorage ps) {
//		List<PurchaseStorageDetail> psds= ps.getPurchaseStorageDetailList();
//		if(CollectionUtils.isNotEmpty(psds)){
//			List<Long> detailIdList = new ArrayList<Long>();
//			psds.forEach(psd->{
//				detailIdList.add(psd.getId());
//			});
//			purchaseStorageDetailMapper.deleteBatchIds(detailIdList);
//		}
//		this.baseMapper.deleteById(ps.getId());
//	}
//
//	/**
//	 * 更新采购任务的入库数量
//	 * @param ps
//	 */
//	public void updateTaskDailyDetailInCount(PurchaseStorage ps){
//		List<PurchaseStorageDetail>  psds = ps.getPurchaseStorageDetailList();
//		Map<Long,Integer> taskDetails  = Maps.newHashMap();
//		for(PurchaseStorageDetail psd:psds){
//			Long taskId = psd.getTaskDailyDetailId();
//			if(taskId!=null){
//				if(taskDetails.containsKey(taskId)){
//					Integer a = taskDetails.get(taskId).intValue()+psd.getQuantity()+psd.getTransQuantity();
//					taskDetails.put(taskId, a);
//				}else{
//					taskDetails.put(taskId, psd.getQuantity()+psd.getTransQuantity());
//				}
//			}
//		}
//		if(CollectionUtils.isNotEmpty(taskDetails.keySet())){
//		List<BuyerTaskDetailDO> tdds  = taskDailyDetailMapper.selectBatchIds(Lists.newArrayList(taskDetails.keySet()));
//		if(CollectionUtils.isNotEmpty(tdds)){
//			for(BuyerTaskDetailDO tdd : tdds){
//				if(taskDetails.containsKey(tdd.getId())){
//					int h = tdd.getInCount()==null?0:tdd.getInCount();//已入库数量
//					int d = h+taskDetails.get(tdd.getId());
//					tdd.setInCount(d);
//					tdd.setGmtModify(new Date());
//				}
//			}
//			taskDailyDetailMapper.updateBatchById(tdds);
//		}
//		}
//	}
//
//	@Override
//	public JsonResult<String> mergePurchaseStorage(String ids) {
//    	JsonResult<String> result = new JsonResult<String>();
//    	List<Long> storageIdList = HaiJsonUtils.toBean(ids, new TypeReference<List<Long>>(){});
//    	PurchaseStorage purchaseStorage = purchaseStorageService.selectById(storageIdList.get(0));
//    	purchaseStorage.setStoOrderNo("G"+DateUtil.formatDate(new Date(), DateUtil.DATE_PARTEN_YYMMDD)+"U"+String.format("%0"+4+"d", purchaseStorage.getBuyerId())+sequenceUtilService.gainPOSequence());
//    	purchaseStorage.setId(null);
//    	purchaseStorageService.insert(purchaseStorage);
//
//    	Map<Long, PurchaseStorageDetail> purchaseStorageDetailMap = new HashMap<Long, PurchaseStorageDetail>();
//
//    	for (Long storageId : storageIdList) {
//    		PurchaseStorage selPurchaseStorage = purchaseStorageService.selectById(storageId);
//    		if (selPurchaseStorage.getStatus() != 0) {
//    			throw new ErpCommonException("入库单状态不正确！");
//    		}
//    		selPurchaseStorage.setStatus(GeneralStatus.CLOSE.getCode());
//    		purchaseStorageService.updateById(selPurchaseStorage);
//			List<PurchaseStorageDetail> queryPurStoDetails = purchaseStorageDetailMapper.queryPurStoDetails(storageId);
//			for (PurchaseStorageDetail purchaseStorageDetail : queryPurStoDetails) {
//				purchaseStorageDetail.setStorageId(purchaseStorage.getId());
//				purchaseStorageDetail.setStoOrderNo(purchaseStorage.getStoOrderNo());
//				purchaseStorageDetail.setId(null);
//
//				PurchaseStorageDetail purchaseStorageDetailTwo = purchaseStorageDetailMap.get(purchaseStorageDetail.getSkuId());
//				if(purchaseStorageDetailTwo != null) {
//					purchaseStorageDetailTwo.setQuantity(purchaseStorageDetailTwo.getQuantity() + purchaseStorageDetail.getQuantity());
//					purchaseStorageDetailTwo.setTransQuantity(purchaseStorageDetailTwo.getTransQuantity() + purchaseStorageDetail.getTransQuantity());
//				} else {
//					purchaseStorageDetailMap.put(purchaseStorageDetail.getSkuId(), purchaseStorageDetail);
//				}
//			}
//		}
//    	List<PurchaseStorageDetail> mapValuesList = new ArrayList<PurchaseStorageDetail>(purchaseStorageDetailMap.values());
//    	purchaseStorageDetailMapper.insertBatch(mapValuesList);
//    	return result.buildIsSuccess(true);
//    }
//}
