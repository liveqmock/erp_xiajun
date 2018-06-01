package com.wangqin.globalshop.item.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.biz1.app.vo.InventoryAddVO;
import com.wangqin.globalshop.item.app.service.IInventoryService;

@Service
public class InventoryServiceImpl implements IInventoryService {

    @Autowired
    private InventoryMapperExt inventoryMapper;

    @Override
    public InventoryAddVO queryInvBySkuCode(String skuCode) {
    	return inventoryMapper.queryInvBySkuCode(skuCode);
    }
    
    @Override
    public void lockVirtualInv(InventoryAddVO inventory) {
    	inventoryMapper.lockVirtualInv(inventory);
    }
    
    @Override
    public InventoryDO queryInventoryBySkuId(Long itemId, Long skuId) {
        return inventoryMapper.getInventoryBySkuId(itemId, skuId);
    }

    @Override
    public void insertBatchInventory(List<InventoryAddVO> inventoryList) {
        inventoryMapper.insertBatchInventory(inventoryList);
    }

    @Override
    public InventoryDO queryInventoryBySkuCode(String skuCode) {
        return inventoryMapper.queryInventoryBySkuCode(skuCode);
    }
    

    @Override
    public void deleteInvBySkuCode(String skuCode) {
    	inventoryMapper.deleteInvBySkuCode(skuCode);
    }
    
    /**
     * 清除虚拟库存
     */
    @Override
    public void updateVirtualInvByItemCode(String itemCode) {
    	inventoryMapper.updateVirtualInvByItemCode(itemCode);
    }
}
