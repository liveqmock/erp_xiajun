package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.customsOrderDO;

public interface customsOrderDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(customsOrderDO record);

    int insertSelective(customsOrderDO record);

    customsOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(customsOrderDO record);

    int updateByPrimaryKey(customsOrderDO record);
}