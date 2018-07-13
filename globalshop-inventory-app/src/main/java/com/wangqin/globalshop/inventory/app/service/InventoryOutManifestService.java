package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryOutManifestVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 出库单相关服务类
 *
 * @author angus
 * @date 2018/7/12
 */
public interface InventoryOutManifestService {

    /**
     * 查询出库单列表
     *
     * @param inventoryOutManifestVO inventoryOutManifestVO
     * @return List<InventoryOutManifestDO>
     */
    List<InventoryOutManifestDO> listInventoryOutManifest(InventoryOutManifestVO inventoryOutManifestVO);

    /**
     * 添加出货单
     *
     * @param inventoryOutManifestDO inventoryOutManifestDO
     * @return
     */
    void insertInventoryOutManifest(InventoryOutManifestDO inventoryOutManifestDO);

    /**
     * 添加出货单
     *
     * @param warehouseNo   仓库号
     * @param warehouseName 仓库名
     * @param remark        备注
     * @return
     */
    InventoryOutManifestDO insertInventoryOutManifest(String warehouseNo, String warehouseName, String remark);
}
