package com.wangqin.globalshop.purchase.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerStorageDetailVo;

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

    public List<BuyerStorageDetailVo> searchByOpenId(String openId);

    public List<BuyerStorageDetailVo> searchByopenidAndUpc(String openId, String upc);

    public List<BuyerStorageDetailVo> searchAll();

    public void comfirm(BuyerStorageDetailVo detailVo);

    public List<BuyerStorageDetailVo> queryHasComfirm();

    public List<BuyerStorageDetailVo> queryComfirmWithParam(String buyerOpenId, String upc);

    public void deleteById(Long id);

    public void updateMem(Long id, String mem);


}
