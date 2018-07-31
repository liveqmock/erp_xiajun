package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryOutManifestQueryVO;

import java.util.List;

/**
 * 出库单相关服务类
 *
 * @author angus
 * @date 2018/7/12
 */
public interface InventoryOutManifestService {

    /**
     * 根据指定条件分页查询出库单记录
     *
     * @param inventoryOutManifestQueryVO
     * @param pageQueryParam
     * @return List<InventoryOutManifestDO>
     */
    List<InventoryOutManifestDO> listInventoryOutManifest(InventoryOutManifestQueryVO inventoryOutManifestQueryVO,
                                                          PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询出库单记录数目
     *
     * @param inventoryOutManifestQueryVO
     * @return
     */
    int countInventoryOutManifest(InventoryOutManifestQueryVO inventoryOutManifestQueryVO);

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
