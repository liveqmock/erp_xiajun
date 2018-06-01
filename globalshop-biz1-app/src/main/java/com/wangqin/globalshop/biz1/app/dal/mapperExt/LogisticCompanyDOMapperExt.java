package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.LogisticCompanyDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.LogisticCompanyDOMapper;

import java.util.List;

public interface LogisticCompanyDOMapperExt extends LogisticCompanyDOMapper {

    List<LogisticCompanyDO> selectList();
}