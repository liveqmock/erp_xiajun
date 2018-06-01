package com.wangqin.globalshop.inventory.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryOutVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryOutManifestDOMapper;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.inventory.app.service.IInventoryOutManifestDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public class InventoryOutManifestDetailServiceImpl implements IInventoryOutManifestDetailService {
    @Autowired
    private InventoryOutManifestDOMapper mapper;

    @Override
    public void addInventoryOut(InventoryOutManifestDO inventoryOut) {
        mapper.insertSelective(inventoryOut);
    }

    @Override
    public InventoryOutManifestDO queryInventoryOut(Long id) {
        // TODO: 18.6.1
        return mapper.selectByPrimaryKey(id);
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
    public Set<String> updateInventoryOutConfirm(InventoryOutManifestDO inventoryOut) {
        return null;
    }

    @Override
    public void deleteInventoryOutById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
