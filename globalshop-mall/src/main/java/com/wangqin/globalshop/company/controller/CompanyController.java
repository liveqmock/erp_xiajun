package com.wangqin.globalshop.company.controller;

import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商户管理 controller
 *
 * @author angus
 * @date 2018/8/6
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public Object addCompany(CompanyEditVO companyEditVO) {
        JsonResult result = new JsonResult();

        try {
            companyService.addCompany(companyEditVO);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("操作出错！").buildIsSuccess(false);
        }

        return result;
    }

    @PostMapping("/get")
    public Object getCompany(String companyNo) {
        JsonResult<CompanyDO> result = new JsonResult<>();

        try {
            CompanyDO companyDO = companyService.getCompany(companyNo);
            result.buildData(companyDO).buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            result.buildMsg("查询出错").buildIsSuccess(false);
        }

        return result;
    }

    @PostMapping("/list")
    public Object listCompanies(CompanyQueryVO companyQueryVO, PageQueryParam pageQueryParam) {
        JsonPageResult<List<CompanyItemVO>> result = new JsonPageResult<>();

        try {
            List<CompanyItemVO> companyItemVOList = companyService.listCompanies(companyQueryVO, pageQueryParam);
            int totalCount = companyService.countCompanies(companyQueryVO);
            result.buildData(companyItemVOList).buildTotalCount(totalCount).buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            result.buildMsg("查询出错").buildIsSuccess(false);
        }

        return result;
    }

//    public Object updateCompany() {
//
//    }
}
