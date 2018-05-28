package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CompanyDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/5/25
 */
public interface CompanyDOMapperExt extends CompanyDOMapper {

	public List<CompanyDO> queryPoList(CompanyDO company);


}
