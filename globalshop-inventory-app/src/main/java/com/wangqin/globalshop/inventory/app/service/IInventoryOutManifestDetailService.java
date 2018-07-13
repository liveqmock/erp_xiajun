package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryOutVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;

import java.util.List;
import java.util.Set;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface IInventoryOutManifestDetailService {
    void addInventoryOut(InventoryOutManifestDO inventoryOut);

    InventoryOutManifestDO queryInventoryOut(Long id);

    InventoryOutManifestDO selectById(Long id);

    void updateInventoryOut(InventoryOutManifestDO inventoryOut);

    JsonPageResult<List<InventoryOutManifestDO>> inventoryOutQueryList(InventoryOutVO inventoryOutVO);

    Set<String> addInventoryOutConfirm(InventoryOutManifestDO inventoryOut);

    Set<String> updateInventoryOutConfirm(InventoryOutManifestDO inventoryOut);

    void deleteInventoryOutById(Long id);

    /**
     * 添加出货单详情
     *
     * @param inventoryOutManifestDetailDO
     */
    void insertInventoryOutManifestDetail(InventoryOutManifestDetailDO inventoryOutManifestDetailDO);

    /**
     * 添加出货单详情
     *
     * @param inventoryOnWareHouseDO
     * @param inventoryOutManifestDO
     */
    void insertInventoryOutManifestDetail(InventoryOnWareHouseDO inventoryOnWareHouseDO,
                                          InventoryOutManifestDO inventoryOutManifestDO, Long quantity);
}
