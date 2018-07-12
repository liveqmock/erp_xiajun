package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;

/**
 * 出货单相关服务类
 *
 * @author angus
 * @date 2018/7/12
 */
public interface InventoryOutManifestService {
    /**
     * 添加出货单
     * @param inventoryOutManifest
     * @return
     */
    void addInventoryOutManifest(InventoryOutManifestDO inventoryOutManifest);
}
