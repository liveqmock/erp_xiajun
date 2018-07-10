package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.constants.enums.GeneralStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerStorageDetailVo;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.*;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.StringUtil;
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
    private ItemSkuScaleMapperExt skuScaleMapperExt;

    @Autowired
    private AuthUserDOMapperExt userMapperExt;

    @Autowired
    private BuyerDOMapperExt buyerDOMapperExt;


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
    public List<BuyerStorageDetailVo> searchByOpenId(String openId){

        List<BuyerStorageDetailVo> voList = doSearchList(openId,null,Integer.valueOf(GeneralStatus.INIT.getCode()));
        return voList;
    }

    @Override
    public List<BuyerStorageDetailVo> searchByopenidAndUpc(String openId, String upc){

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
    public List<BuyerStorageDetailVo> queryComfirmWithParam(String openId, String upc){
        List<BuyerStorageDetailVo> voList = doSearchList(openId,upc,Integer.valueOf(GeneralStatus.CONFIRM.getCode()));
        return voList;
    }

    /**
     * 只查询状态为预入库状态的入库单
     * @param openId
     * @param upc
     * @return
     */
    @Transactional(rollbackFor = ErpCommonException.class)
    private List<BuyerStorageDetailVo> doSearchList(String openId, String upc, Integer status){
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
            	System.out.println(detail.getSkuCode() + ":" + detail.getUpc());
                ItemSkuDO skuSo = new ItemSkuDO();
                skuSo.setSkuCode(detail.getSkuCode());
                skuSo.initCompany();
                skuSo.setUpc(detail.getUpc());


                ItemSkuDO skuDO = skuDOMapperExt.queryItemSku(skuSo);

                List<ItemSkuScaleDO> scaleList = skuScaleMapperExt.selectScaleNameValueBySkuCode(skuDO.getSkuCode());

                System.out.println(skuDO);
                if(skuDO == null){
                    throw new ErpCommonException("未找到对应商品");
                }


                UserQueryVO user = null;
                if(!EasyUtil.isStringEmpty(detail.getOpUserNo())){
                    user = userMapperExt.selectUserVoByUserNo(detail.getOpUserNo());
                }

                BuyerStorageDetailVo vo = new BuyerStorageDetailVo();

                vo.setId(detail.getId());

                BuyerDO buyerSo = new BuyerDO();
                buyerSo.setOpenId(buyerStorage.getBuyerOpenId()+"");
                buyerSo.setCompanyNo(AppUtil.getLoginUserCompanyNo());

                BuyerDO buyer = buyerDOMapperExt.searchBuyer(buyerSo);

                vo.setBuyerName(buyer.getNickName());
                vo.setBuyerOpenId(buyer.getOpenId());

                vo.setGmtCreate(detail.getGmtCreate());
                vo.setGmtModify(detail.getGmtModify());
                vo.setStorageNo(buyerStorage.getStorageNo());
                vo.setItemCode(detail.getItemCode());
                vo.setUpc(detail.getUpc());
                vo.setSkuName(skuDO.getItemName());
                vo.setSpecifications(getScaleString(scaleList));
                vo.setSkuCode(skuDO.getSkuCode());
                vo.setQuantity(detail.getQuantity()+detail.getTransQuantity());//线下加在途,实际需要入库的数量
                vo.setTransQuantity(detail.getQuantity()+detail.getTransQuantity());//线下加在途，预入库数量

                vo.setStatus(detail.getStatus());
                vo.setStatusName(GeneralStatus.of(detail.getStatus()).getDescription());
                vo.setOpTime(detail.getOpTime());
                vo.setOpUserNo(detail.getOpUserNo());

                vo.setGmtCreate(detail.getGmtCreate());
                vo.setGmtModify(detail.getGmtModify());

                vo.setBatchNum(detail.getBatchNum());

                vo.setBuyerTaskNo(buyerStorage.getBuyerTaskNo());

                if(user != null){
                    vo.setOpUserName(user.getLoginName());
                }
                vo.setCompanyNo(buyerStorage.getCompanyNo());
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
    @Transactional(rollbackFor = ErpCommonException.class)
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
        if(detailVo.getQuantity() == null || detailVo.getQuantity() < 0){
            throw new ErpCommonException("入库数必填");
        }
        //detail.setQuantity(detailVo.getQuantity());

        //记录货架号
        if(EasyUtil.isStringEmpty(detailVo.getShelfNo())){
            throw new ErpCommonException("货架号必填");
        }
        detail.setShelfNo(detailVo.getShelfNo());
        //库存修改,含inventory,inventory_warehouse
        // 记录流水inventory_inout
        InventoryDO inventory = new InventoryDO();

        inventory.setInv(Long.valueOf(detailVo.getQuantity()));//客户实际改写后的数量
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

    @Transactional(rollbackFor = ErpCommonException.class)
    public void deleteById(Long id){
        BuyerStorageDetailDO detail = detaiMapper.selectByPrimaryKey(id);
        detaiMapper.deleteByPrimaryKey(id);
        if(detail != null && !EasyUtil.isStringEmpty(detail.getStorageNo())){
            BuyerStorageDetailDO detailSo = new BuyerStorageDetailDO();
            detailSo.setStorageNo(detail.getStorageNo());
            List<BuyerStorageDetailDO> detailDOList = detaiMapper.searchList(detailSo);
            if(EasyUtil.isListEmpty(detailDOList)){
                BuyerStorageDO buyerStorageSo = new BuyerStorageDO();
                buyerStorageSo.setStorageNo(detail.getStorageNo());
                BuyerStorageDO buyerStorage = mapper.search(buyerStorageSo);
                if(buyerStorage != null){
                    mapper.deleteByPrimaryKey(buyerStorage.getId());
                }
            }
        }
    }
    @Transactional(rollbackFor = ErpCommonException.class)
    public void updateMem(Long id, String mem){
        BuyerStorageDetailDO detail = detaiMapper.selectByPrimaryKey(id);
        detail.setMem(EasyUtil.truncateLEFitSize(mem,1000));
        detaiMapper.updateByPrimaryKey(detail);
    }


    private String getScaleString(List<ItemSkuScaleDO> scaleList){
        String result = "";
        if(!EasyUtil.isListEmpty(scaleList)){
            for(ItemSkuScaleDO scale : scaleList){
                result += scale.getScaleName()+"-"+scale.getScaleValue()+";";
            }
        }
        return result;
    }

}
