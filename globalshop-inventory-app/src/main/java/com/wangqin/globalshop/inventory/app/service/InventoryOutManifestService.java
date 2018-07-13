package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import org.springframework.stereotype.Service;

/**
 * 出货单相关服务类
 *
 * @author angus
 * @date 2018/7/12
 */
public interface InventoryOutManifestService {
    /**
     * 添加出货单
     *
     * @param inventoryOutManifest
     * @return
     */
    void insertInventoryOutManifest(InventoryOutManifestDO inventoryOutManifest);

    /**
     * 添加出货单
     *
     * @param warehouseNo
     * @param warehouseName
     * @param desc
     * @return
     */
    InventoryOutManifestDO insertInventoryOutManifest(String warehouseNo, String warehouseName, String desc);
}
