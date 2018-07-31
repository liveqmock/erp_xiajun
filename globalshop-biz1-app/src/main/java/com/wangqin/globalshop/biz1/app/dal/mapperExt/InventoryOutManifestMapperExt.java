package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryOutManifestQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryOutManifestDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryOutVO;
import org.apache.ibatis.annotations.Param;

public interface InventoryOutManifestMapperExt extends InventoryOutManifestDOMapper {

    Integer queryInventoryOutCount(InventoryOutVO inventoryOutVO);

    void updateIsdelById(InventoryOutManifestDO inventoryOutManifestDO);

    /**
     * 根据指定条件分页查询出库单记录
     *
     * @param inventoryOutManifestQueryVO
     * @param pageQueryParam
     * @return
     */
    List<InventoryOutManifestDO> listInventoryOutManifest(
            @Param("inventoryOutManifestQueryVO") InventoryOutManifestQueryVO inventoryOutManifestQueryVO,
            @Param("pageQueryParam") PageQueryParam pageQueryParam);


    /**
     * 根据指定条件查询出库单记录数目
     *
     * @param inventoryOutManifestQueryVO
     * @return
     */
    int countInventoryOutManifest(
            @Param("inventoryOutManifestQueryVO") InventoryOutManifestQueryVO inventoryOutManifestQueryVO);
}
