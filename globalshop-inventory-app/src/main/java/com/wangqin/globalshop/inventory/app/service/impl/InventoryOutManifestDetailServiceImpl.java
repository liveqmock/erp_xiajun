package com.wangqin.globalshop.inventory.app.service.impl;

import com.google.common.collect.Sets;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryOutVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOutManifestDetailDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOutManifestMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.inventory.app.service.IInventoryOutManifestDetailService;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author biscuit
 * @data 2018/06/01
 */
@Service
public class InventoryOutManifestDetailServiceImpl implements IInventoryOutManifestDetailService {
    @Autowired
    private InventoryOutManifestMapperExt mapper;
    @Autowired
    private InventoryOutManifestDetailDOMapperExt detailMapper;
    @Autowired
    private InventoryService inventoryService;

    @Override
    public void addInventoryOut(InventoryOutManifestDO inventoryOut) {
        mapper.insertSelective(inventoryOut);
    }

    @Override
    public InventoryOutManifestDO queryInventoryOut(Long inventoryOutId) {
        if (inventoryOutId == null) {
            throw new RuntimeException("the item id is null");
        }
        InventoryOutManifestDO inventoryOut = mapper.selectByPrimaryKey(inventoryOutId);
        if (inventoryOut != null) {
//            EntityWrapper<InventoryOutDetail> entityWrapper = new EntityWrapper<InventoryOutDetail>();
//            entityWrapper.where("inventory_out_id={0}", inventoryOutId);

//            List<InventoryOutManifestDetailDO> inventoryOutDetailList = detailMapper.findByInventoryOutNo(inventoryOut.getInventoryOutNo());
//            if(inventoryOut != null){
//                inventoryOut.setInventoryOutDetailList(inventoryOutDetailList);
//            }
//        }
        }
            return inventoryOut;

    }

    @Override
    public InventoryOutManifestDO selectById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateInventoryOut(InventoryOutManifestDO inventoryOut) {
        mapper.updateByPrimaryKeySelective(inventoryOut);
    }

    @Override
    public JsonPageResult<List<InventoryOutManifestDO>> inventoryOutQueryList(InventoryOutVO inventoryOutVO) {
        return null;
    }

    @Override
    public Set<String> addInventoryOutConfirm(InventoryOutManifestDO inventoryOut) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public Set<String> updateInventoryOutConfirm(InventoryOutManifestDO inventoryOut) {
        //修改出库单
        mapper.updateByPrimaryKeySelective(inventoryOut);
        //库存出库
        return inventoryCheckOutBatch(inventoryOut);
    }
    public Set<String> inventoryCheckOutBatch(InventoryOutManifestDO inventoryOut) {
        Set<String> skuIdSet = Sets.newHashSet();
        List<InventoryOutManifestDetailDO> inventoryOutDetailList = detailMapper.selectByOutNo(inventoryOut.getInventoryOutNo());
        Long warehouseId = inventoryOut.getId();
        inventoryOutDetailList.forEach(inventoryOutDetail -> {
            Long quantity = inventoryOutDetail.getQuantity();
            if (warehouseId== null || inventoryOutDetail.getQuantity() == null) {
                throw new ErpCommonException("有空数据");
            } else {
                if (inventoryOutDetail.getQuantity() <= 0) {
                    throw new ErpCommonException("减少的库存要为正数");
                }
                inventoryService.inventoryCheckOut(warehouseId, quantity);
                skuIdSet.add(inventoryOutDetail.getSkuCode());
            }
        });
        return skuIdSet;
    }

    @Override
    public void deleteInventoryOutById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertInventoryOutManifestDetail(InventoryOutManifestDetailDO inventoryOutManifestDetailDO) {
        detailMapper.insert(inventoryOutManifestDetailDO);
    }

    @Override
    public void insertInventoryOutManifestDetail(InventoryOnWareHouseDO inventoryOnWareHouseDO,
                                                 InventoryOutManifestDO inventoryOutManifestDO, Long quantity) {
        InventoryOutManifestDetailDO inventoryOutManifestDetailDO = new InventoryOutManifestDetailDO();

        inventoryOutManifestDetailDO.setInventoryOutNo(inventoryOutManifestDO.getInventoryOutNo());
        inventoryOutManifestDetailDO.setItemCode(inventoryOnWareHouseDO.getItemCode());
        inventoryOutManifestDetailDO.setQuantity(quantity);
        inventoryOutManifestDetailDO.setItemName(inventoryOnWareHouseDO.getItemName());
        inventoryOutManifestDetailDO.setScale(inventoryOnWareHouseDO.getScale());
        inventoryOutManifestDetailDO.setUpc(inventoryOnWareHouseDO.getUpc());
        inventoryOutManifestDetailDO.setSkuCode(inventoryOnWareHouseDO.getSkuCode());
        inventoryOutManifestDetailDO.setSkuPic(inventoryOnWareHouseDO.getSkuPic());
        inventoryOutManifestDetailDO.setShelfNo(inventoryOnWareHouseDO.getShelfNo());
        inventoryOutManifestDetailDO.setCompanyNo(inventoryOnWareHouseDO.getCompanyNo());
        inventoryOutManifestDetailDO.setModifier(AppUtil.getLoginUserId());
        inventoryOutManifestDetailDO.setCreator(AppUtil.getLoginUserId());
        inventoryOutManifestDetailDO.setIsDel(false);

        insertInventoryOutManifestDetail(inventoryOutManifestDetailDO);
    }
}
