package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.DealerTypeDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.DealerTypeDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DealerTypeDOMapper {
    int countByExample(DealerTypeDOExample example);

    int deleteByExample(DealerTypeDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DealerTypeDO record);

    int insertSelective(DealerTypeDO record);

    List<DealerTypeDO> selectByExample(DealerTypeDOExample example);

    DealerTypeDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DealerTypeDO record, @Param("example") DealerTypeDOExample example);

    int updateByExample(@Param("record") DealerTypeDO record, @Param("example") DealerTypeDOExample example);

    int updateByPrimaryKeySelective(DealerTypeDO record);

    int updateByPrimaryKey(DealerTypeDO record);
}