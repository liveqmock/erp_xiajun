package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyerDOMapper {
    int countByExample(BuyerDOExample example);

    int deleteByExample(BuyerDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerDO record);

    int insertSelective(BuyerDO record);

    List<BuyerDO> selectByExample(BuyerDOExample example);

    BuyerDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerDO record, @Param("example") BuyerDOExample example);

    int updateByExample(@Param("record") BuyerDO record, @Param("example") BuyerDOExample example);

    int updateByPrimaryKeySelective(BuyerDO record);

    int updateByPrimaryKey(BuyerDO record);
}