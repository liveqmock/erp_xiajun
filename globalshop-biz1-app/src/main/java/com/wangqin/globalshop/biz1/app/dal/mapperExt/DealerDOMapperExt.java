package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.DealerDOMapper;

import java.util.List;
import java.util.Map;

public interface DealerDOMapperExt extends DealerDOMapper {
    List<Map<String, String>> dealerList(DealerDO seller);
    
    DealerDO selectByCode(String code);
    
}