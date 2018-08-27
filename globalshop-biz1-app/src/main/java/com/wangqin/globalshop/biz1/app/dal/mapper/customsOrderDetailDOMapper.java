package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.customsOrderDetailDO;

public interface customsOrderDetailDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(customsOrderDetailDO record);

    int insertSelective(customsOrderDetailDO record);

    customsOrderDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(customsOrderDetailDO record);

    int updateByPrimaryKey(customsOrderDetailDO record);
}