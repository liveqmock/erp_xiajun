package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.usercenter.service.IResourceService;

import ch.qos.logback.classic.pattern.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description：资源管理
 */
@Controller
@RequestMapping("/resource")
@Authenticated
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    /**
     * 菜单树
     *
     * @return
     */
    @PostMapping("/tree")
    @ResponseBody
    public Object tree() {
        logger.debug("/resource/tree");
        return resourceService.selectTree(AppUtil.getLoginUserId());
    }

    /**
     * 资源管理页
     *
     * @return
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/resource";
    }

    /**
     * 资源管理列表
     *
     * @return
     */
    @PostMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
        return resourceService.selectAll();
    }

    /**
     * 添加资源页
     *
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/resourceAdd";
    }

    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(AuthResourceDO resource, String code) {
        // 选择菜单时将openMode设置为null
    	if(null == AppUtil.getLoginUserId() || null == AppUtil.getLoginUserCompanyNo()) {
    		return renderError("请先登陆");
    	}
    	
    	if(StringUtil.isBlank(resource.getId().toString())) {
    		return renderError("新增不能有ID");
    	}
    	if(StringUtil.isBlank(resource.getPid().toString())) {
    		resource.setPid(00000000L);
    	}else {
    		AuthResourceDO resourceDO = resourceService.queryTreeByResourceId(resource.getPid().toString());
    		if(!EasyUtil.isStringEmpty(resourceDO.getUrl())) {
    			resource.setUrl(resourceDO.getName() + "/" + resource.getUrl());
    		}else {
    			resource.setUrl(resourceDO.getName() + "/" + resource.getName());
    		}
    		
    		resource.setPid(Long.parseLong(resourceDO.getResourceId()));
    		
    	}
    	
        resource.setResourceId(RandomUtils.getTimeRandom());
        resourceService.insert(resource);
        return renderSuccess("添加成功！");
    }

    /**
     * 查询所有的菜单
     */
    @RequestMapping("/allTree")
    @ResponseBody
    public Object allMenu() {
        return resourceService.selectAllMenu();
    }

    /**
     * 查询所有的资源tree
     */
    @RequestMapping("/allTrees")
    @ResponseBody
    public Object allTree() {
        return resourceService.selectAllTree();
    }

    /**
     * 编辑资源页
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
        AuthResourceDO resource = resourceService.selectById(id);
        model.addAttribute("resource", resource);
        return "admin/resourceEdit";
    }

    /**
     * 编辑资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(AuthResourceDO resource) {
        // 选择菜单时将openMode设置为null
        Integer type = resource.getResourceType().intValue();
        if (StringUtil.isBlank(type.toString())) {
            resource.setOpenMode(null);
        }
        resourceService.updateSelectiveById(resource);
        return renderSuccess("编辑成功！");
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        resourceService.deleteById(id);
        return renderSuccess("删除成功！");
    }


    @RequestMapping("/query")
    @ResponseBody
    public Object query(Long id) {
        JsonResult<AuthResourceDO> result = new JsonResult<>();

        return result.buildData(resourceService.selectById(id)).buildIsSuccess(true);
    }

    /**
     * 以列表条目的形式返回所有的资源记录
     * @param
     * @return
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public Object queryList() {
        JsonResult<List<AuthResourceDO>> result = new JsonResult<List<AuthResourceDO>>();

        result.setData(resourceService.queryResource());

        return result.buildIsSuccess(true);
    }

    /**
     *
     * @param
     * @return
     */
    @RequestMapping("/queryTree")
    @ResponseBody
    public Object queryTree() {
        JsonResult<List<AuthResourceDO>> result = new JsonResult<List<AuthResourceDO>>();

        result.setData(resourceService.queryResource());

        return result.buildIsSuccess(true);
    }
}
