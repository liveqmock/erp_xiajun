package com.wangqin.globalshop.usercenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.usercenter.service.IOrganizationService;
import com.wangqin.globalshop.biz1.app.vo.OrganizationQueryVO;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthOrganizationDO;

/**
 * @description：部门管理
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

    @Autowired
    private IOrganizationService organizationService;

    /**
     * 部门管理主页
     *
     * @return
     */
    @GetMapping(value = "/manager")
    public String manager() {
        return "admin/authOrganizationDO";
    }

    /**
     * 部门资源树
     *
     * @return
     */
    @PostMapping(value = "/tree")
    @ResponseBody
    public Object tree() {
            return organizationService.selectTree();
    }

    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
        return organizationService.selectTreeGrid();
    }

    /**
     * 添加部门页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "admin/organizationAdd";
    }

    /**
     * 添加部门
     *
     * @param authOrganizationDO
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid AuthOrganizationDO authOrganizationDO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getDefaultMessage());
            }
            return null;
        }
        String org_id=String.format("%1$09d",RandomUtils.nextInt(1000000000));
        authOrganizationDO.setOrgId(org_id);
        authOrganizationDO.setCode(org_id);
        organizationService.insert(authOrganizationDO);
        return renderSuccess("添加成功！");
    }

    /**
     * 编辑部门页
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        AuthOrganizationDO organization = organizationService.selectById(id);
        request.setAttribute("authOrganizationDO", organization);
        return "admin/organizationEdit";
    }

    /**
     * 编辑部门
     *
     * @param organization
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(AuthOrganizationDO organization) {
        organizationService.updateSelectiveById(organization);
        return renderSuccess("编辑成功！");
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        organizationService.deleteById(id);
        return renderSuccess("删除成功！");
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object query(Long id) {
        JsonResult<AuthOrganizationDO> result = new JsonResult<>();

        return result.buildData(organizationService.selectById(id)).buildIsSuccess(true);
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public Object queryList(OrganizationQueryVO organizationQueryVO) {
        JsonPageResult<List<AuthOrganizationDO>> result = organizationService.queryOrganizationList(organizationQueryVO);

        return result.buildIsSuccess(true);
    }
}
