package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CompanyDOMapperExt;
import com.wangqin.globalshop.usercenter.service.IUserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author biscuit
 * @data 2018/07/26
 */
@Service
public class UserCompanyServiceImpl implements IUserCompanyService{
    @Autowired
    private CompanyDOMapperExt mapper;
    @Override
    public CompanyDO selectByCompanyNo(String companyNo) {
        return mapper.selectByCompanyNo(companyNo);
    }
}
