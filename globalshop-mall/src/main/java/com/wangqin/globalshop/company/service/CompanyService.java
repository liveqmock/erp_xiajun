package com.wangqin.globalshop.company.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyDetailVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;

import java.util.List;

/**
 * 商家管理 service
 *
 * @author angus
 * @date 2018/8/6
 */
public interface CompanyService {

    /**
     * 新增商家
     *
     * @param companyDetailVO
     */
    void addCompany(CompanyDetailVO companyDetailVO);

    /**
     * 根据 companyNo 获取指定商家
     *
     * @param companyNo
     * @return
     */
    CompanyDO getCompany(String companyNo);

    /**
     * 根据 companyNo 获取指定商家
     * 
     * @param companyNo
     * @return
     */
    CompanyDetailVO getCompanyDetailVO(String companyNo);

    /**
     * 根据指定条件分页查询商家
     *
     * @param companyQueryVO
     * @param pageQueryParam
     * @return
     */
    List<CompanyItemVO> listCompanies(CompanyQueryVO companyQueryVO, PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询商家数目
     *
     * @param companyQueryVO
     * @return
     */
    int countCompanies(CompanyQueryVO companyQueryVO);

    /**
     * 更新商家
     *
     * @param companyDO
     */
    void updateCompany(CompanyDO companyDO);

    /**
     * 更新商家
     *
     * @param companyDetailVO
     */
    void updateCompany(CompanyDetailVO companyDetailVO);

    /**
     * 停用商家
     *
     * @param companyNo
     */
    void disableCompany(String companyNo);

    /**
     * 删除商家
     *
     * @param companyNo
     */
    void deleteCompany(String companyNo);

}
