package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryOutManifestVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryOutManifestDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryOutVO;

public interface InventoryOutManifestMapperExt extends InventoryOutManifestDOMapper {

    Integer queryInventoryOutCount(InventoryOutVO inventoryOutVO);

    /**
     * 查询出库单列表
     *
     * @param inventoryOutManifestVO inventoryOutManifestVO
     * @return List<InventoryOutManifestDO>
     */
    List<InventoryOutManifestDO> listInventoryOutManifest(InventoryOutManifestVO inventoryOutManifestVO);

    void updateIsdelById(InventoryOutManifestDO inventoryOutManifestDO);
}
