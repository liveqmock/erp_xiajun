package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderDOMapper;

import java.util.List;

public interface MallOrderDOMapperExt extends MallOrderDOMapper {
    MallOrderDO selectByPrimaryKey(Long id);
    int updateByPrimaryKey(MallOrderDO record);
    MallOrderDO selectByOrderNo(String orderNo);
    List<MallOrderDO> queryByStatus(byte status);
}