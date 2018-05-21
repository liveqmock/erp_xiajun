package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerEntryManifestDO;

public interface BuyerEntryManifestDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerEntryManifestDO record);

    int insertSelective(BuyerEntryManifestDO record);

    BuyerEntryManifestDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerEntryManifestDO record);

    int updateByPrimaryKey(BuyerEntryManifestDO record);
}