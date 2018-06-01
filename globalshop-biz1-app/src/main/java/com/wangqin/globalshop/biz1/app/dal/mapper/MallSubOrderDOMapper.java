package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

public interface MallSubOrderDOMapper {

    int insert(MallSubOrderDO record);
    int updateByPrimaryKeySelective(MallSubOrderDO record);


}