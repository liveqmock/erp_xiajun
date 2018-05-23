package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.CompanyMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.Company;
import com.wangqin.globalshop.biz1.app.service.ICompanyService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Company 表数据服务层接口实现类
 *
 */
@Service
public class CompanyServiceImpl extends SuperServiceImpl<CompanyMapper, Company> implements ICompanyService {


}