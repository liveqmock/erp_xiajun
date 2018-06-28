package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;
import com.wangqin.globalshop.biz1.app.vo.RoleQueryVO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.usercenter.service.IResourceService;
import com.wangqin.globalshop.usercenter.service.IRoleResourceService;
import com.wangqin.globalshop.usercenter.service.IRoleService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description：权限管理
 */
@Controller
@RequestMapping("/role")
@Authenticated
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleResourceService roleResourceService;
    @Autowired
    private IResourceService resourceService;
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
     * 修改权限
     *
     * @param role
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Object update(AuthRoleDO role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getDefaultMessage());
            }
            return null;
        }
        role.setRoleId((long)RandomUtils.nextInt(1000000000));        
        roleService.updateSelectiveById(role);
        return renderSuccess("更新成功！");
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
        
        if(EasyUtil.isStringEmpty(role.getName())) {
        	return renderError("角色名称");
        }
        if(EasyUtil.isStringEmpty(role.getSeq().toString())) {
        	return renderError("排序不能为空");
        }
        if(EasyUtil.isStringEmpty(role.getStatus().toString())) {
        	return renderError("状态不能为空");
        }
        role.setRoleId((long)RandomUtils.nextInt(1000000000));
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
        AuthRoleDO roleDO = roleService.selectById(id);
        roleResourceService.deleteByRoleId(roleDO.getRoleId());
//        List<AuthRoleResourceDO> roleResourceDO = roleResourceService.selectRoleResourceByResourceId(roleDO.getRoleId());
//        for(int i = 0; i < roleResourceDO.size(); i ++) {
//        	roleResourceService.deleteByRoleId(roleResourceDO.get(i).getRoleId());
//        }
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
        if(EasyUtil.isStringEmpty(role.getName())) {
        	return renderError("角色名称");
        }
        if(EasyUtil.isStringEmpty(role.getSeq().toString())) {
        	return renderError("排序不能为空");
        }
        if(EasyUtil.isStringEmpty(role.getStatus().toString())) {
        	return renderError("状态不能为空");
        }
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
    @RequestMapping("/updateGrant")
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
        String companyNo=AppUtil.getLoginUserCompanyNo();
        logger.info("current CompanyNo is " + companyNo);
        roleQueryVO.setCompanyNo(companyNo);
        JsonPageResult<List<AuthRoleDO>> result = roleService.queryRoleList(roleQueryVO);

        return result.buildIsSuccess(true);
    }

//    /**
//     * 在角色授权的时候要根据不同的公司显示不同的授权的内容
//     * @return
//     */
//    @RequestMapping("/queryTree")
//    @ResponseBody
//    public Object queryTree() {
//    	JsonResult<List<AuthResourceDO>> result = new JsonResult<>();
//    	List<AuthResourceDO> resourceList = new ArrayList<>();
//    	String companyNo = AppUtil.getLoginUserCompanyNo();
//    	
//    	List<AuthRoleResourceDO> roleResourceList = roleResourceService.queryRoleResourceByCompanyNo(companyNo);
//    	for(AuthRoleResourceDO roleResource : roleResourceList) {
//    		 String resourceId = roleResource.getResourceId().toString();
//    		 AuthResourceDO resource = resourceService.queryTreeByResourceId(resourceId);
//    		 resourceList.add(resource);
//    	}
//    	result.setData(resourceList);
//    	return result.buildIsSuccess(true);
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
