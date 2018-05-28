package com.wangqin.globalshop.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import src.main.java.com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import src.main.java.com.wangqin.globalshop.biz1.app.dal.mapper.CompanyDOMapper;
import src.main.java.com.wangqin.globalshop.biz1.app.vo.JsonResult;

@Controller
@RequestMapping("/wx/company")
public class CompanyController {

    @Autowired
    CompanyDOMapper companyDOMapper;

    @RequestMapping("/queryById")
    @ResponseBody
    public Object queryById(Long id) {
        JsonResult<CompanyDO> result = new JsonResult<>();
        CompanyDO company = companyDOMapper.selectByPrimaryKey(id);
        result.buildData(company).buildIsSuccess(true);
        return result;
    }

}
