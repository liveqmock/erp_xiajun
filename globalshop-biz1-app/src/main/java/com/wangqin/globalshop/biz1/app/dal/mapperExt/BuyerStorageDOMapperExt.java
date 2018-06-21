package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerStorageDOMapper;

import java.util.List;

public interface BuyerStorageDOMapperExt extends BuyerStorageDOMapper {
    List<BuyerStorageDO> list(BuyerStorageDO buyerStorage);


    List<BuyerStorageDO> searchList(BuyerStorageDO buyerStorageDO);

    BuyerStorageDO search(BuyerStorageDO buyerStorageDO);

    Long searchCount(BuyerStorageDO buyerStorageDO);
}
