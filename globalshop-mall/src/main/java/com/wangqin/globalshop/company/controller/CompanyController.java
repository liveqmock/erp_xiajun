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
 * 商家管理 controller
 *
 * @author angus
 * @date 2018/8/6
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 添加商家
     *
     * @param companyDetailVO
     * @return
     */
    @PostMapping("/add")
    public Object addCompany(CompanyDetailVO companyDetailVO) {
        JsonResult result = new JsonResult();

        try {
            companyService.addCompany(companyDetailVO);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("操作出错！").buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 查询单个商家
     *
     * @param companyNo
     * @return
     */
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

    /**
     * 查询单个商家
     *
     * @param companyNo
     * @return
     */
    @PostMapping("/get")
    public Object getCompanyDetailVO(String companyNo) {
        JsonResult<CompanyDetailVO> result = new JsonResult<>();

        try {
            CompanyDetailVO companyDetailVO = companyService.getCompanyDetailVO(companyNo);
            result.buildData(companyDetailVO).buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            result.buildMsg("查询出错").buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 分页查询多个商家
     *
     * @param companyQueryVO
     * @param pageQueryParam
     * @return
     */
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

    /**
     * 更新商家
     *
     * @return
     */
    @PostMapping("/update")
    public Object updateCompany(CompanyDetailVO companyDetailVO) {
        JsonResult result = new JsonResult();

        try {
            companyService.updateCompany(companyDetailVO);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("操作出错！").buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 停用商家
     *
     * @param companyNo
     * @return
     */
    @PostMapping("/disable")
    public Object disableCompany(String companyNo) {
        JsonResult result = new JsonResult();

        try {
            companyService.disableCompany(companyNo);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("操作出错！").buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 删除商家
     *
     * @param companyNo
     * @return
     */
    @PostMapping("/delete")
    public Object deleteCompany(String companyNo) {
        JsonResult result = new JsonResult();

        try {
            companyService.deleteCompany(companyNo);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg()).buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("操作出错！").buildIsSuccess(false);
        }

        return result;
    }
}
