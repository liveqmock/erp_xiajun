package com.wangqin.globalshop.company.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyEditVO;

/**
 * 商户管理 service
 *
 * @author angus
 * @date 2018/8/6
 */
public interface CompanyService {

    /**
     * 新增商户
     *
     * @param companyEditVO
     */
    void addCompany(CompanyEditVO companyEditVO);
}
