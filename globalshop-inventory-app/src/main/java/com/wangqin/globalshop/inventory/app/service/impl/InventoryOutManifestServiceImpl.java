package com.wangqin.globalshop.inventory.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOutManifestMapperExt;
import com.wangqin.globalshop.inventory.app.service.InventoryOutManifestService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author angus
 * @date 2018/7/12
 */
public class InventoryOutManifestServiceImpl implements InventoryOutManifestService {

    @Autowired
    private InventoryOutManifestMapperExt inventoryOutManifestMapperExt;

    /**
     * 添加出货单
     *
     * @param inventoryOutManifest
     */
    @Override
    public void addInventoryOutManifest(InventoryOutManifestDO inventoryOutManifest) {

    }
}
