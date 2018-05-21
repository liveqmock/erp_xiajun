package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerTypeDO;

public interface DealerTypeDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DealerTypeDO record);

    int insertSelective(DealerTypeDO record);

    DealerTypeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DealerTypeDO record);

    int updateByPrimaryKey(DealerTypeDO record);
}