package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.DealerDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.DealerDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DealerDOMapper {
    int countByExample(DealerDOExample example);

    int deleteByExample(DealerDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DealerDO record);

    int insertSelective(DealerDO record);

    List<DealerDO> selectByExample(DealerDOExample example);

    DealerDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DealerDO record, @Param("example") DealerDOExample example);

    int updateByExample(@Param("record") DealerDO record, @Param("example") DealerDOExample example);

    int updateByPrimaryKeySelective(DealerDO record);

    int updateByPrimaryKey(DealerDO record);
}