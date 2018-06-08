package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallReturnOrderDOMapper;

import java.util.List;

public interface MallReturnOrderDOMapperExt extends MallReturnOrderDOMapper {
    List<MallReturnOrderDO> list();
}