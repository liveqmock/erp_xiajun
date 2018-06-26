package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.constants.enums.GeneralStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerStorageDetailVo;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerStorageDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerStorageDetailMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.purchase.app.service.IBuyerStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private AuthUserDOMapperExt userMapperExt;


    @Autowired
    private InventoryService inventoryService;

    @Override
    public List<BuyerStorageDO> list(BuyerStorageDO buyerStorage) {
        buyerStorage.initCompany();
        return mapper.list(buyerStorage);
    }


    @Override
    public List<BuyerStorageDO> searchList(BuyerStorageDO buyerStorageDO){
        return mapper.searchList(buyerStorageDO);
    }

    @Override
    public BuyerStorageDO search(BuyerStorageDO buyerStorageDO){
        return mapper.search(buyerStorageDO);
    }

    @Override
    public Long searchCount(BuyerStorageDO buyerStorageDO){
        return mapper.searchCount(buyerStorageDO);
    }

    @Override
    public List<BuyerStorageDetailVo> searchByOpenId(Long openId){

        List<BuyerStorageDetailVo> voList = doSearchList(openId,null,Integer.valueOf(GeneralStatus.INIT.getCode()));
        return voList;
    }

    @Override
    public List<BuyerStorageDetailVo> searchByopenidAndUpc(Long openId, String upc){

        List<BuyerStorageDetailVo> voList = doSearchList(openId,upc,Integer.valueOf(GeneralStatus.INIT.getCode()));
        return voList;
    }

    @Override
    public List<BuyerStorageDetailVo> searchAll(){
        List<BuyerStorageDetailVo> voList = doSearchList(null,null,Integer.valueOf(GeneralStatus.INIT.getCode()));
        return voList;
    }

    @Override
    public List<BuyerStorageDetailVo> queryHasComfirm(){
        List<BuyerStorageDetailVo> voList = doSearchList(null,null,Integer.valueOf(GeneralStatus.CONFIRM.getCode()));
        return voList;
    }


    @Override
    public List<BuyerStorageDetailVo> queryComfirmWithParam(Long openId, String upc){
        List<BuyerStorageDetailVo> voList = doSearchList(openId,upc,Integer.valueOf(GeneralStatus.CONFIRM.getCode()));
        return voList;
    }

    /**
     * 只查询状态为预入库状态的入库单
     * @param openId
     * @param upc
     * @return
     */
    private List<BuyerStorageDetailVo> doSearchList(Long openId, String upc, Integer status){
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
            if(status != null){
                detailSo.setStatus(status);
            }
            if(!EasyUtil.isStringEmpty(upc)){
                detailSo.setUpc(upc);
            }
            List<BuyerStorageDetailDO> detailDOList = detaiMapper.searchList(detailSo);

            for(BuyerStorageDetailDO detail : detailDOList){

                ItemSkuDO skuSo = new ItemSkuDO();
                skuSo.setSkuCode(detail.getSkuCode());
                skuSo.initCompany();
                skuSo.setUpc(detail.getUpc());


                ItemSkuDO skuDO = skuDOMapperExt.queryItemSku(skuSo);
                if(skuDO == null){
                    throw new ErpCommonException("未找到对应商品");
                }


                AuthUserDO user = null;
                if(!EasyUtil.isStringEmpty(detail.getOpUserNo())){
                    user = userMapperExt.selectUserVoByUserNo(detail.getOpUserNo());
                }

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

                vo.setStatus(detail.getStatus());
                vo.setStatusName(GeneralStatus.of(detail.getStatus()).getDescription());
                vo.setOpTime(detail.getOpTime());
                vo.setOpUserNo(user.getUserNo());
                if(user != null){
                    vo.setOpUserName(user.getLoginName());
                }
                voList.add(vo);
            }
        }
        return voList;
    }

    /**
     * 确认入库
     * @param detailVo
     */
    @Override
    @Transactional
    public void comfirm(BuyerStorageDetailVo detailVo){

        //修改状态
        BuyerStorageDetailDO detail = detaiMapper.selectByPrimaryKey(detailVo.getId());

        if(!Integer.valueOf(GeneralStatus.INIT.getCode()).equals(detail.getStatus())){
            throw new ErpCommonException("状态非入库状态，status:"+detail.getStatus());
        }

        detail.setStatus(GeneralStatus.CONFIRM.getCode());
        //修改入库仓库
        if(EasyUtil.isStringEmpty(detailVo.getWarehouseName()) || EasyUtil.isStringEmpty(detailVo.getWarehouseNo())){
            throw new ErpCommonException("仓库为空，请重新选择");
        }
        detail.setWarehouseName(detailVo.getWarehouseName());
        detail.setWarehouseNo(detailVo.getWarehouseNo());

        //检验库存
        if(detailVo.getQuantity() < 0){
            throw new ErpCommonException("确认入库数量不能小于0");
        }
        detail.setQuantity(detailVo.getQuantity());

        //记录货架号
        if(EasyUtil.isStringEmpty(detailVo.getShelfNo())){
            throw new ErpCommonException("货架号必填");
        }
        detail.setShelfNo(detailVo.getShelfNo());
        //库存修改,含inventory,inventory_warehouse
        // 记录流水inventory_inout
        InventoryDO inventory = new InventoryDO();

        inventory.setInv(Long.valueOf(detail.getQuantity()));
        inventory.setItemCode(detail.getItemCode());
        inventory.setSkuCode(detail.getSkuCode());
        inventory.setCompanyNo(AppUtil.getLoginUserCompanyNo());

        inventoryService.outbound(inventory,detail.getWarehouseNo(),detail.getShelfNo());

        //保存明细
        detail.setOpTime(new Date());
        detail.setOpUserNo(AppUtil.getLoginUserId());
        detaiMapper.updateByPrimaryKey(detail);

        //记录头部状态，校验
        BuyerStorageDO buyerStorageSo = new BuyerStorageDO();
        buyerStorageSo.initCompany();
        buyerStorageSo.setStorageNo(detail.getStorageNo());
        BuyerStorageDO buyerStorage = mapper.search(buyerStorageSo);
        if(buyerStorage == null){
            throw new ErpCommonException("buyer_storage未找到,storageNo: "+detail.getStorageNo());
        }

        if(storageAllComfirm(detail.getStorageNo())){
            //全部确认
             buyerStorage.setStatus(GeneralStatus.CONFIRM.getCode());
        }else {
            //未全部确认
            buyerStorage.setStatus(GeneralStatus.CONFIRMING.getCode());
        }
        mapper.updateByPrimaryKey(buyerStorage);
    }

    private Boolean storageAllComfirm(String storageNo){
        Boolean comfirm = true;
        BuyerStorageDetailDO detailSo = new BuyerStorageDetailDO();
        detailSo.setStorageNo(storageNo);

        List<BuyerStorageDetailDO> detailDOList = detaiMapper.searchList(detailSo);

        for(BuyerStorageDetailDO detail : detailDOList){
            if(Integer.valueOf(GeneralStatus.INIT.getCode()).equals(detail.getStatus())
                    || Integer.valueOf(GeneralStatus.CONFIRMING.getCode()).equals(detail.getStatus())){
                //新建，或确认中
                comfirm = false;
                break;
            }
        }
        return comfirm;
    }


    public void deleteById(Long id){
        BuyerStorageDetailDO detail = detaiMapper.selectByPrimaryKey(id);
        detail.setIsDel(true);
        detaiMapper.updateByPrimaryKey(detail);
    }

    public void updateMem(Long id, String mem){
        BuyerStorageDetailDO detail = detaiMapper.selectByPrimaryKey(id);
        detail.setMem(EasyUtil.truncateLEFitSize(mem,1000));
        detaiMapper.updateByPrimaryKey(detail);
    }


}
