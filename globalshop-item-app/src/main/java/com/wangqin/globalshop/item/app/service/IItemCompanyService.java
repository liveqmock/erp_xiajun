package com.wangqin.globalshop.item.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;

public interface IItemCompanyService {

	CompanyDO selectByCompanyNo(String companyNo);
}
