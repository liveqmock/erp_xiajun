package com.wangqin.globalshop.company.controller;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyEditVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            result.buildMsg(e.getErrorMsg())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("操作出错！")
                    .buildIsSuccess(false);
        }
        return  result;
    }
}
