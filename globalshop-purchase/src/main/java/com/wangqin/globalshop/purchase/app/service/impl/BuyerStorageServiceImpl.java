package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerStorageDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerStorageDOMapperExt;
import com.wangqin.globalshop.purchase.app.service.IBuyerStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@Service
public class BuyerStorageServiceImpl implements IBuyerStorageService {
    @Autowired
    private BuyerStorageDOMapperExt mapper;
    @Override
    public List<BuyerStorageDO> list(BuyerStorageDO buyerStorage) {
        buyerStorage.initCompany();
        return mapper.list(buyerStorage);
    }
}
