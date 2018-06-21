package com.wangqin.globalshop.purchase.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerStorageDetailVo;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
public interface IBuyerStorageService {
    List<BuyerStorageDO> list(BuyerStorageDO buyerStorage);


    List<BuyerStorageDO> searchList(BuyerStorageDO buyerStorageDO);

    BuyerStorageDO search(BuyerStorageDO buyerStorageDO);

    Long searchCount(BuyerStorageDO buyerStorageDO);

    public List<BuyerStorageDetailVo> searchByOpenId(Long openId);

    public List<BuyerStorageDetailVo> searchByopenidAndUpc(Long openId, String upc);

    public List<BuyerStorageDetailVo> searchAll();


}
