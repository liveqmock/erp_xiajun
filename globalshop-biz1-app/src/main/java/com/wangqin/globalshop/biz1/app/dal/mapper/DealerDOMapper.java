package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;

public interface DealerDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DealerDO record);

    int insertSelective(DealerDO record);

    DealerDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DealerDO record);

    int updateByPrimaryKey(DealerDO record);
}