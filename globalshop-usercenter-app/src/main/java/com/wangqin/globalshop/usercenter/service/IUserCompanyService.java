package com.wangqin.globalshop.usercenter.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;

/**
 * @author biscuit
 * @data 2018/07/26
 */
public interface IUserCompanyService {
    CompanyDO selectByCompanyNo(String companyNo);
}
