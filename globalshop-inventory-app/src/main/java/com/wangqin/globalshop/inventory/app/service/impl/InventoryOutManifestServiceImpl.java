package com.wangqin.globalshop.inventory.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOutManifestMapperExt;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.inventory.app.service.InventoryOutManifestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author angus
 * @date 2018/7/12
 */
@Service
public class InventoryOutManifestServiceImpl implements InventoryOutManifestService {

    @Autowired
    private InventoryOutManifestMapperExt inventoryOutManifestMapperExt;

    /**
     * 添加出货单
     */
    @Override
    public void insertInventoryOutManifest(InventoryOutManifestDO inventoryOutManifest) {
        inventoryOutManifestMapperExt.insert(inventoryOutManifest);
    }

    /**
     * 添加出货单
     */
    @Override
    public InventoryOutManifestDO insertInventoryOutManifest(String warehouseNo, String warehouseName, String desc) {
        InventoryOutManifestDO inventoryOutManifestDO = new InventoryOutManifestDO();

        inventoryOutManifestDO.setInventoryOutNo(CodeGenUtil.getInventoryOutNo());
        inventoryOutManifestDO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
        inventoryOutManifestDO.setWarehouseNo(warehouseNo);
        inventoryOutManifestDO.setWarehouseName(warehouseName);
        inventoryOutManifestDO.setStatus(0);
        inventoryOutManifestDO.setRemark(desc);
        inventoryOutManifestDO.setModifier(AppUtil.getLoginUserId());
        inventoryOutManifestDO.setCreator(AppUtil.getLoginUserId());
        inventoryOutManifestDO.setIsDel(false);

        insertInventoryOutManifest(inventoryOutManifestDO);
        return inventoryOutManifestDO;
    }

}
