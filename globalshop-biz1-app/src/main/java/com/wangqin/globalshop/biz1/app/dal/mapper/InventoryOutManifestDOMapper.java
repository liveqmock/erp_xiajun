package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;

public interface InventoryOutManifestDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryOutManifestDO record);

    int insertSelective(InventoryOutManifestDO record);

    InventoryOutManifestDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryOutManifestDO record);

    int updateByPrimaryKey(InventoryOutManifestDO record);
}