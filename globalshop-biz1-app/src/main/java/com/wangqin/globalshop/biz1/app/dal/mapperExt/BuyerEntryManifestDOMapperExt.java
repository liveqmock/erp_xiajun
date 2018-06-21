package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerEntryManifestDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerEntryManifestDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/6/21
 */
public interface BuyerEntryManifestDOMapperExt extends BuyerEntryManifestDOMapper {

	BuyerEntryManifestDO searchEntryManifest(BuyerEntryManifestDO buyerEntryManifestDO);

	Long searchEntryManifestCount(BuyerEntryManifestDO buyerEntryManifestDO);


	List<BuyerEntryManifestDO> searchEntryManifestList(BuyerEntryManifestDO buyerEntryManifestDO);

}
