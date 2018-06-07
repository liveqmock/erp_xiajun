package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.DealerDOMapper;

import java.util.List;

public interface DealerDOMapperExt extends DealerDOMapper {
    List<DealerDO> list();

    DealerDO selectByCode(String code);
}