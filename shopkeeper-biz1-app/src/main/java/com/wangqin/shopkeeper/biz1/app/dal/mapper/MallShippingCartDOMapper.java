package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallShippingCartDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallShippingCartDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallShippingCartDOMapper {
    int countByExample(MallShippingCartDOExample example);

    int deleteByExample(MallShippingCartDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallShippingCartDO record);

    int insertSelective(MallShippingCartDO record);

    List<MallShippingCartDO> selectByExample(MallShippingCartDOExample example);

    MallShippingCartDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallShippingCartDO record, @Param("example") MallShippingCartDOExample example);

    int updateByExample(@Param("record") MallShippingCartDO record, @Param("example") MallShippingCartDOExample example);

    int updateByPrimaryKeySelective(MallShippingCartDO record);

    int updateByPrimaryKey(MallShippingCartDO record);
}