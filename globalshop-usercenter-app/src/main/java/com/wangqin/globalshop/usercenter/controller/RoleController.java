package com.wangqin.globalshop.usercenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.biz1.app.vo.RoleQueryVO;
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
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.usercenter.service.IRoleService;

/**
 * @description：权限管理
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    /**
     * 权限管理页
     *
     * @return
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/role";
    }

    /**
     * 权限列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @PostMapping("/dataGrid")
    @ResponseBody
    public Object dataGrid(Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);

        roleService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 权限树
     *
     * @return
     */
    @PostMapping("/tree")
    @ResponseBody
    public Object tree() {
        return roleService.selectTree();
    }

    /**
     * 添加权限页
     *
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/roleAdd";
    }

    /**
     * 添加权限
     *
     * @param role
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(AuthRoleDO role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getDefaultMessage());
            }
            return null;
        }
        role.setRoleId((long)RandomUtils.nextInt(1000000000));
        role.setCompanyNo("0");
//        role.setStatus(Byte.valueOf("0"));
        roleService.insert(role);
        return renderSuccess("添加成功！");
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        roleService.deleteById(id);
        return renderSuccess("删除成功！");
    }

    /**
     * 编辑权限页
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
        AuthRoleDO role = roleService.selectById(id);
        model.addAttribute("role", role);
        return "admin/roleEdit";
    }

    /**
     * 删除权限
     *
     * @param role
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(AuthRoleDO role) {
        roleService.updateSelectiveById(role);
        return renderSuccess("编辑成功！");
    }

    /**
     * 授权页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/grantPage")
    public String grantPage(Long id, Model model) {
        model.addAttribute("id", id);
        return "admin/roleGrant";
    }

    /**
     * 授权页面页面根据角色查询资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/findResourceIdListByRoleId")
    @ResponseBody
    public Object findResourceByRoleId(Long id) {
        List<Long> resources = roleService.selectResourceIdListByRoleId(id);
        return renderSuccess(resources);
    }

    /**
     * 授权
     *
     * @param id
     * @param resourceIds
     * @return
     */
    @RequestMapping("/grant")
    @ResponseBody
    public Object grant(Long id, String resourceIds) {
        roleService.updateRoleResource(id, resourceIds);
        return renderSuccess("授权成功！");
    }


    @RequestMapping("/query")
    @ResponseBody
    public Object query(Long id) {
        JsonResult<AuthRoleDO> result = new JsonResult<>();

        return result.buildData(roleService.selectById(id)).buildIsSuccess(true);
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public Object queryList(RoleQueryVO roleQueryVO) {
        JsonPageResult<List<AuthRoleDO>> result = roleService.queryRoleList(roleQueryVO);

        return result.buildIsSuccess(true);
    }

//    /**
//     * 授权页面页面根据角色查询资源
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping("/queryResourceIdListByRoleId")
//    @ResponseBody
//    public Object findResourceByRoleId(Long id) {
//        JsonResult<List<Long>> result = new JsonResult<List<Long>>();
//        List<Long> resources = roleService.queryResourceIdListByRoleId(id);
//        result.buildData(resources);
//        return result;
//    }

    @RequestMapping("/resCodes")
    @ResponseBody
    public Object resCodes() {
        JsonResult<Set<String>> result = new JsonResult<>();
//        AppUtil.getLoginUserId();
        // 读取用户的url和角色
        return result.buildData(roleService.queryUserResCodes(AppUtil.getLoginUserId())).buildIsSuccess(true);
    }
}
