package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerEntryManifestDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerEntryManifestDOMapperExt;
import com.wangqin.globalshop.purchase.app.service.IBuyerEntryManifestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Create by 777 on 2018/6/21
 */
public class BuyerEntryManifestServiceImpl implements IBuyerEntryManifestService {

	@Autowired
	private BuyerEntryManifestDOMapperExt entryManifestDOMapperExt;


	BuyerEntryManifestDO searchEntryManifest(BuyerEntryManifestDO buyerEntryManifestDO){
		return entryManifestDOMapperExt.searchEntryManifest(buyerEntryManifestDO);
	}

	Long searchEntryManifestCount(BuyerEntryManifestDO buyerEntryManifestDO){
		return entryManifestDOMapperExt.searchEntryManifestCount(buyerEntryManifestDO);
	}


	List<BuyerEntryManifestDO> searchEntryManifestList(BuyerEntryManifestDO buyerEntryManifestDO){
		return entryManifestDOMapperExt.searchEntryManifestList(buyerEntryManifestDO);
	}





}
