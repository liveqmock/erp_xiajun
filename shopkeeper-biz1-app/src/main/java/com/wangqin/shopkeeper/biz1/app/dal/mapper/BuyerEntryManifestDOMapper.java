package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerEntryManifestDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerEntryManifestDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyerEntryManifestDOMapper {
    int countByExample(BuyerEntryManifestDOExample example);

    int deleteByExample(BuyerEntryManifestDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerEntryManifestDO record);

    int insertSelective(BuyerEntryManifestDO record);

    List<BuyerEntryManifestDO> selectByExample(BuyerEntryManifestDOExample example);

    BuyerEntryManifestDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerEntryManifestDO record, @Param("example") BuyerEntryManifestDOExample example);

    int updateByExample(@Param("record") BuyerEntryManifestDO record, @Param("example") BuyerEntryManifestDOExample example);

    int updateByPrimaryKeySelective(BuyerEntryManifestDO record);

    int updateByPrimaryKey(BuyerEntryManifestDO record);
}