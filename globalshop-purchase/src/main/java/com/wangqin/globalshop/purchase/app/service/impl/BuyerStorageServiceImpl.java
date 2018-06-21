package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerStorageDetailVo;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerStorageDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerStorageDetailMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.purchase.app.service.IBuyerStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@Service
public class BuyerStorageServiceImpl implements IBuyerStorageService {

    @Autowired
    private BuyerStorageDOMapperExt mapper;


    @Autowired
    private BuyerStorageDetailMapperExt detaiMapper;

    @Autowired
    private ItemSkuMapperExt skuDOMapperExt;

    @Override
    public List<BuyerStorageDO> list(BuyerStorageDO buyerStorage) {
        buyerStorage.initCompany();
        return mapper.list(buyerStorage);
    }


    public List<BuyerStorageDO> searchList(BuyerStorageDO buyerStorageDO){
        return mapper.searchList(buyerStorageDO);
    }

    public BuyerStorageDO search(BuyerStorageDO buyerStorageDO){
        return mapper.search(buyerStorageDO);
    }

    public Long searchCount(BuyerStorageDO buyerStorageDO){
        return mapper.searchCount(buyerStorageDO);
    }

    public List<BuyerStorageDetailVo> searchByOpenId(Long openId){

        List<BuyerStorageDetailVo> voList = doSearchList(openId,null);
        return voList;
    }

    public List<BuyerStorageDetailVo> searchByopenidAndUpc(Long openId, String upc){

        List<BuyerStorageDetailVo> voList = doSearchList(openId,upc);
        return voList;
    }

    public List<BuyerStorageDetailVo> searchAll(){
        List<BuyerStorageDetailVo> voList = doSearchList(null,null);
        return voList;
    }

    private List<BuyerStorageDetailVo> doSearchList(Long openId, String upc){
        List<BuyerStorageDetailVo> voList = new ArrayList<>();

        BuyerStorageDO buyerStorageSo = new BuyerStorageDO();
        buyerStorageSo.initCompany();
        if(!EasyUtil.isStringEmpty(openId+"")){
            buyerStorageSo.setBuyerOpenId(openId);
        }


        List<BuyerStorageDO> storages = mapper.searchList(buyerStorageSo);
        if(EasyUtil.isListEmpty(storages)){
            return voList;
        }

        for(BuyerStorageDO buyerStorage : storages){
            BuyerStorageDetailDO detailSo = new BuyerStorageDetailDO();
            detailSo.setStorageNo(buyerStorage.getStorageNo());
            if(!EasyUtil.isStringEmpty(upc)){
                detailSo.setUpc(upc);
            }
            List<BuyerStorageDetailDO> detailDOList = detaiMapper.searchList(detailSo);

            for(BuyerStorageDetailDO detail : detailDOList){

                List<ItemSkuDO> skuDOS = skuDOMapperExt.queryItemSkusByUpc(detail.getUpc());
                if(EasyUtil.isListEmpty(skuDOS)){
                    throw new ErpCommonException("未找到对应商品");
                }
                if(skuDOS.size() > 1){
                    throw new ErpCommonException("查找到的UPC商品数量大于1，UPC:"+detail.getUpc());
                }
                ItemSkuDO skuDO = skuDOS.get(0);

                BuyerStorageDetailVo vo = new BuyerStorageDetailVo();

                vo.setId(detail.getId());

                vo.setBuyerName(buyerStorage.getBuyerName());
                vo.setBuyerOpenId(buyerStorage.getBuyerOpenId());
                vo.setGmtCreate(detail.getGmtCreate());
                vo.setGmtModify(detail.getGmtModify());
                vo.setStorageNo(buyerStorage.getStorageNo());
                vo.setItemCode(detail.getItemCode());
                vo.setUpc(detail.getUpc());
                vo.setSkuName(skuDO.getItemName());
                vo.setSpecifications(skuDO.getScale());
                vo.setSkuCode(skuDO.getSkuCode());
                vo.setQuantity(detail.getQuantity());
                vo.setTransQuantity(detail.getTransQuantity());
                voList.add(vo);
            }
        }
        return voList;
    }


}
