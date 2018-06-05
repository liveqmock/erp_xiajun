package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDetailDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryOutManifestDetailDOMapper;

import java.util.List;

public interface InventoryOutManifestDetailDOMapperExt extends InventoryOutManifestDetailDOMapper {

    List<InventoryOutManifestDetailDO> selectByOutNo(String inventoryOutNo);
}