package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemSkuScaleDOMapper {
    int countByExample(ItemSkuScaleDOExample example);

    int deleteByExample(ItemSkuScaleDOExample example);

    int insert(ItemSkuScaleDO record);

    int insertSelective(ItemSkuScaleDO record);

    List<ItemSkuScaleDO> selectByExample(ItemSkuScaleDOExample example);

    int updateByExampleSelective(@Param("record") ItemSkuScaleDO record, @Param("example") ItemSkuScaleDOExample example);

    int updateByExample(@Param("record") ItemSkuScaleDO record, @Param("example") ItemSkuScaleDOExample example);
}