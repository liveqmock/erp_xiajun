package com.wangqin.globalshop.biz1.app.service.company;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.wangqin.globalshop.biz1.app.dal.dataObject.Company;
import com.wangqin.globalshop.biz1.app.dal.mapper.CompanyDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.CompanyMapper;
import com.wangqin.globalshop.biz1.app.service.ICompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * Company 表数据服务层接口实现类
 *
 */
@Service
public class CompanyServiceImpl implements ICompanyService {


	@Resource CompanyDOMapperExt companyDOMapperExt;


}
