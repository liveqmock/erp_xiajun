package com.wangqin.globalshop.company.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyEditVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;

import java.util.List;

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

    /**
     * 根据 companyNo 获取指定商户
     *
     * @param companyNo
     * @return
     */
    CompanyDO getCompany(String companyNo);

    /**
     * 根据指定条件分页查询商户
     *
     * @param companyQueryVO
     * @param pageQueryParam
     * @return
     */
    List<CompanyItemVO> listCompanies(CompanyQueryVO companyQueryVO, PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询商户数目
     *
     * @param companyQueryVO
     * @return
     */
    int countCompanies(CompanyQueryVO companyQueryVO);

    /**
     * 更新商户
     *
     * @param companyDO
     */
    void updateCompany(CompanyDO companyDO);

    /**
     * 停用商户
     *
     * @param companyNo
     */
    void disableCompany(String companyNo);

    /**
     * 删除商户
     *
     * @param companyNo
     */
    void deleteCompany(String companyNo);
}
