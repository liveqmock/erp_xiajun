package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerTypeDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.DealerTypeDOMapper;

import java.util.List;

public interface DealerTypeDOMapperExt extends DealerTypeDOMapper {
    List<DealerTypeDO> list(String companyNo);
    
    int countRelativeDealerType(String typeCode);
    
//    int inserByNoId(DealerTypeDO record);
    
    List<DealerTypeDO> listNoCompanyNo();
}