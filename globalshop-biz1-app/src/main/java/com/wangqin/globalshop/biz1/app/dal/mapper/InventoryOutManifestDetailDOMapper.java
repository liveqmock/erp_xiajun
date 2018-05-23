package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDetailDO;

public interface InventoryOutManifestDetailDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryOutManifestDetailDO record);

    int insertSelective(InventoryOutManifestDetailDO record);

    InventoryOutManifestDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryOutManifestDetailDO record);

    int updateByPrimaryKey(InventoryOutManifestDetailDO record);
}