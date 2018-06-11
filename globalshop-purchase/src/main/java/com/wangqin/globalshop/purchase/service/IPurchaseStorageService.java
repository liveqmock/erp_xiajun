package com.wangqin.globalshop.purchase.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.PurchaseStorageVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.JsonResult;

import java.util.List;

public interface IPurchaseStorageService  {
	
	/**
	 * 入库
	 */
	void putInStorage(BuyerStorageDO ps, List<BuyerReceiptDO> recs);
	
	
	/**
	 * 手动入库
	 * @param ps
	 */
	void addPurchaseStorage(BuyerStorageDO ps);
	
	
	/**
	 * 更新
	 * @param ps
	 */
	void updatePurchaseStorage(BuyerStorageDO ps);
	
	/**
	 * 确认入库,普通修改确认入库
	 * @param ps
	 */
	void confirmPurchaseStorage(BuyerStorageDO ps);
	
	
	/**
	 * 确认入库,新增确认入库
	 * @param ps
	 */
	void addConfirmPurchaseStorage(BuyerStorageDO ps);
	
	
	
	BuyerStorageDO queryPurchaseStorage(Long purStoId);
	
	JsonPageResult<List<BuyerStorageDO>> queryPurStorages(PurchaseStorageVO psVO);
	
	/**
	 * 删除入库单
	 * @param ps
	 */
	void deletePurchaseStorage(BuyerStorageDO ps);


	JsonResult<String> mergePurchaseStorage(String ids);
}
