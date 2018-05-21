package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemSkuDOMapper {
    int countByExample(ItemSkuDOExample example);

    int deleteByExample(ItemSkuDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemSkuDO record);

    int insertSelective(ItemSkuDO record);

    List<ItemSkuDO> selectByExampleWithBLOBs(ItemSkuDOExample example);

    List<ItemSkuDO> selectByExample(ItemSkuDOExample example);

    ItemSkuDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemSkuDO record, @Param("example") ItemSkuDOExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemSkuDO record, @Param("example") ItemSkuDOExample example);

    int updateByExample(@Param("record") ItemSkuDO record, @Param("example") ItemSkuDOExample example);

    int updateByPrimaryKeySelective(ItemSkuDO record);

    int updateByPrimaryKeyWithBLOBs(ItemSkuDO record);

    int updateByPrimaryKey(ItemSkuDO record);
}