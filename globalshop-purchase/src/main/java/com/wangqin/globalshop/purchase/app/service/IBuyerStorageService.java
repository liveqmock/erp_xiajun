package com.wangqin.globalshop.purchase.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
public interface IBuyerStorageService {
    List<BuyerStorageDO> list(BuyerStorageDO buyerStorage);
}
