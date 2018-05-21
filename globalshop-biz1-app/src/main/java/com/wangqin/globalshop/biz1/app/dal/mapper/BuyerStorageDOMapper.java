package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyerStorageDOMapper {
    int countByExample(BuyerStorageDOExample example);

    int deleteByExample(BuyerStorageDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerStorageDO record);

    int insertSelective(BuyerStorageDO record);

    List<BuyerStorageDO> selectByExample(BuyerStorageDOExample example);

    BuyerStorageDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerStorageDO record, @Param("example") BuyerStorageDOExample example);

    int updateByExample(@Param("record") BuyerStorageDO record, @Param("example") BuyerStorageDOExample example);

    int updateByPrimaryKeySelective(BuyerStorageDO record);

    int updateByPrimaryKey(BuyerStorageDO record);
}