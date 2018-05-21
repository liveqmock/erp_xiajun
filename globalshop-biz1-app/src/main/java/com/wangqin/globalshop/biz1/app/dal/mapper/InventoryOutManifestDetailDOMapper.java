package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOutManifestDetailDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventoryOutManifestDetailDOMapper {
    int countByExample(InventoryOutManifestDetailDOExample example);

    int deleteByExample(InventoryOutManifestDetailDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InventoryOutManifestDetailDO record);

    int insertSelective(InventoryOutManifestDetailDO record);

    List<InventoryOutManifestDetailDO> selectByExample(InventoryOutManifestDetailDOExample example);

    InventoryOutManifestDetailDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InventoryOutManifestDetailDO record, @Param("example") InventoryOutManifestDetailDOExample example);

    int updateByExample(@Param("record") InventoryOutManifestDetailDO record, @Param("example") InventoryOutManifestDetailDOExample example);

    int updateByPrimaryKeySelective(InventoryOutManifestDetailDO record);

    int updateByPrimaryKey(InventoryOutManifestDetailDO record);
}