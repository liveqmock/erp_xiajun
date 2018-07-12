package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.vo.InventoryOutVO;

public interface InventoryOutManifestMapperExt {

	Integer queryInventoryOutCount(InventoryOutVO inventoryOutVO);
	
	List<InventoryOutManifestDO> queryInventoryOutList(InventoryOutVO inventoryOutVO);

//	InventoryOutManifestDO insertInventoryOutManifest(InventoryOutManifestVO inventoryOutManifestDO);
}
