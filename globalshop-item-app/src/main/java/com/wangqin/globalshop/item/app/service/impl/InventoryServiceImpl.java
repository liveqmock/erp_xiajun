package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.item.app.service.IInventoryService;

@Service
public class InventoryServiceImpl implements IInventoryService {

    @Autowired
    private InventoryMapperExt inventoryMapper;

    @Override
    public InventoryDO queryInventoryBySkuId(Long itemId, Long skuId) {
        return inventoryMapper.getInventoryBySkuId(itemId, skuId);
    }

    @Override
    public void insertBatch(List<InventoryDO> invList) {
        inventoryMapper.insertBatch(invList);
    }

    @Override
    public InventoryDO queryInventoryBySkuCode(String skuCode) {
        return inventoryMapper.queryInventoryBySkuCode(skuCode);
    }
}
