package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDetailDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryOutManifestVO;

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

    Set<String> addInventoryOutConfirm(InventoryOutManifestDO inventoryOut);

    Set<String> updateInventoryOutConfirm(InventoryOutManifestDO inventoryOut);

    void deleteInventoryOutById(Long id);

    /**
     * 查询出货单详情列表
     *
     * @param inventoryOutNo
     * @return
     */
    List<InventoryOutManifestDetailDO> listByInventoryOutNo(String inventoryOutNo);

    /**
     * 查询出货单详情列表
     *
     * @param inventoryOutManifestVO inventoryOutManifestVO
     * @return List<InventoryOutManifestDO>
     */
    List<InventoryOutManifestDetailDO> listByInventoryOutManifestVO(InventoryOutManifestVO inventoryOutManifestVO);

    /**
     * 添加出货单详情
     *
     * @param inventoryOutManifestDetailDO inventoryOutManifestDetailDO
     */
    void insertInventoryOutManifestDetail(InventoryOutManifestDetailDO inventoryOutManifestDetailDO);

    /**
     * 添加出货单详情
     *
     * @param inventoryOnWareHouseDO 用于获取库存相关信息
     * @param inventoryOutManifestDO 用于获取出货单相关信息
     * @param quantity               出货数量
     */
    void insertInventoryOutManifestDetail(InventoryOnWareHouseDO inventoryOnWareHouseDO,
                                          InventoryOutManifestDO inventoryOutManifestDO, Long quantity);
}
