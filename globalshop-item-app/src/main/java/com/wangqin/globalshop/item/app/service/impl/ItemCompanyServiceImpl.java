package com.wangqin.globalshop.item.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CompanyDOMapperExt;
import com.wangqin.globalshop.item.app.service.IItemCompanyService;

@Service
public class ItemCompanyServiceImpl implements IItemCompanyService{

	@Autowired
	private CompanyDOMapperExt mapperExt;
	
	@Override
	public CompanyDO selectByCompanyNo(String companyNo) {
		return mapperExt.selectByCompanyNo(companyNo); 
	}
}
