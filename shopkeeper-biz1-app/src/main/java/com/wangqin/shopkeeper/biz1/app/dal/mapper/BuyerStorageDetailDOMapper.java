package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerStorageDetailDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerStorageDetailDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyerStorageDetailDOMapper {
    int countByExample(BuyerStorageDetailDOExample example);

    int deleteByExample(BuyerStorageDetailDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerStorageDetailDO record);

    int insertSelective(BuyerStorageDetailDO record);

    List<BuyerStorageDetailDO> selectByExample(BuyerStorageDetailDOExample example);

    BuyerStorageDetailDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerStorageDetailDO record, @Param("example") BuyerStorageDetailDOExample example);

    int updateByExample(@Param("record") BuyerStorageDetailDO record, @Param("example") BuyerStorageDetailDOExample example);

    int updateByPrimaryKeySelective(BuyerStorageDetailDO record);

    int updateByPrimaryKey(BuyerStorageDetailDO record);
}