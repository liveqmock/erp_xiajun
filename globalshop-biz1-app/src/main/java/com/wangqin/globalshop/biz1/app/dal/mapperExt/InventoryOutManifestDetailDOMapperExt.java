package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryOutManifestQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDetailDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryOutManifestDetailDOMapper;

import java.util.List;

/**
 * @author angus
 * @date 2018/7/16
 */
public interface InventoryOutManifestDetailDOMapperExt extends InventoryOutManifestDetailDOMapper {
    /**
     * 获取出货详情单列表
     *
     * @param inventoryOutNo
     * @return
     */
    List<InventoryOutManifestDetailDO> listByInventoryOutNo(String inventoryOutNo);

    /**
     * 获取出货详情单列表
     *
     * @param inventoryOutManifestQueryVO
     * @return
     */
    List<InventoryOutManifestDetailDO> listByInventoryOutManifestVO(InventoryOutManifestQueryVO inventoryOutManifestQueryVO);
}