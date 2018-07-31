package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.api.dto.response.BaseResp;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ResourceQueryVO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.LogWorker;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.usercenter.service.IResourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.validation.Valid;

/**
 * @description：资源管理
 */
@Controller
@RequestMapping("/resource")
@Authenticated
public class ResourceController extends BaseController {
	protected static Logger log = LoggerFactory.getLogger("System");
    @Autowired
    private IResourceService resourceService;

    /**
     * 菜单树
     *
     * @return
     */
    @PostMapping("/tree")
    @ResponseBody
    public Object tree(@Valid AuthResourceDO resourceDo, BindingResult result) {
    	
    	LogWorker.logStart(log, "配置", "userVo{}", resourceDo);
        
        if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");  
   	
        logger.debug("/resource/tree");
        LogWorker.logEnd(log, "配置", "usreDo:{}", resourceDo);
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
    public Object treeGrid(@Valid AuthResourceDO resourceDo, BindingResult result) {
    	LogWorker.logStart(log, "配置", "userVo{}", resourceDo);
        
        if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
        
        LogWorker.logEnd(log, "配置", "usreDo:{}", resourceDo);
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
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object add(@Valid ResourceQueryVO resouceVo, AuthResourceDO resourceDo, String code, BindingResult result) {
        LogWorker.logStart(log, "配置", "resourceVo:{}", resourceDo);
        
        if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        
        BaseResp resp = BaseResp.createSuccess("");
        
        // 选择菜单时将openMode设置为null
    	if(null == AppUtil.getLoginUserId() || null == AppUtil.getLoginUserCompanyNo()) {
    		return renderError("请先登陆");
    	}
    	
    	if(resouceVo.getPid() == null) {
    		resouceVo.setPid(00000000L);
    	}else {
    		ResourceQueryVO resourceQueryVo = resourceService.queryTreeVoByResourceId(resourceDo.getPid().toString());
    		if(!EasyUtil.isStringEmpty(resourceQueryVo.getUrl())) {
    			resouceVo.setUrl(resourceQueryVo.getName() + "/" + resourceDo.getUrl());
    		}else {
    			resouceVo.setUrl(resourceQueryVo.getName() + "/" + resourceDo.getName());
    		}
    		
    		resouceVo.setPid(Long.parseLong(resourceQueryVo.getResourceId()));
    		
    	}
    	
    	resouceVo.setResourceId(RandomUtils.getTimeRandom());
        resourceService.insertByNoId(resouceVo);
        LogWorker.logEnd(log, "配置", "resourceVo:{}", resouceVo);
        return renderSuccess("添加成功！");
    }

    /**
     * 查询所有的菜单
     */
    @RequestMapping("/allTree")
    @ResponseBody
    public Object allMenu(@Valid AuthResourceDO resourceDo, BindingResult result) {
    	LogWorker.logStart(log, "配置", "userVo{}", resourceDo);
        
        if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
        
        LogWorker.logEnd(log, "配置", "usreDo:{}", resourceDo);
        return resourceService.selectAllMenu();
    }

    /**
     * 查询所有的资源tree
     */
    @RequestMapping("/allTrees")
    @ResponseBody
    public Object allTree(@Valid AuthResourceDO resourceDo, BindingResult result) {
    	LogWorker.logStart(log, "配置", "userVo{}", resourceDo);
        
        if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
        
        LogWorker.logEnd(log, "配置", "usreDo:{}", resourceDo);
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
    public Object edit(@Valid ResourceQueryVO resourceVo, AuthResourceDO resourceDo,BindingResult result) {
    	LogWorker.logStart(log, "配置", "resourceVo:{}", resourceVo);
    	if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        
        BaseResp resp = BaseResp.createSuccess("");
        
        // 选择菜单时将openMode设置为null
        Integer type = resourceVo.getResourceType().intValue();
        if (StringUtil.isBlank(type.toString())) {
        	resourceVo.setOpenMode(null);
        }
        resourceService.updateByResourceVo(resourceVo);
        LogWorker.logEnd(log, "配置", "resourceVo:{}", resourceVo);
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
    public Object delete(Long id, @Valid AuthResourceDO resourceDo, BindingResult result) {
    	LogWorker.logStart(log, "配置", "resourceDo:{}", resourceDo);
    	if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        
        BaseResp resp = BaseResp.createSuccess("");
        resourceService.deleteById(id);
        LogWorker.logEnd(log, "配置", "resourceVo:{}", resourceDo);
        return renderSuccess("删除成功！");
    }


    @RequestMapping("/query")
    @ResponseBody
    public Object query(Long id, @Valid AuthResourceDO resourceDo, BindingResult bindResult) {
    	
    	LogWorker.logStart(log, "配置", "resourceDo:{}", resourceDo);
    	if(bindResult.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : bindResult.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        
        BaseResp resp = BaseResp.createSuccess("");
    	
        JsonResult<AuthResourceDO> result = new JsonResult<>();
        LogWorker.logEnd(log, "配置", "resourceVo:{}", resourceDo);
        return result.buildData(resourceService.selectById(id)).buildIsSuccess(true);
    }

    /**
     * 以列表条目的形式返回所有的资源记录
     * @param
     * @return
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public Object queryList( @Valid AuthResourceDO resourceDo, BindingResult bindResult) {
    	
    	LogWorker.logStart(log, "配置", "resourceDo:{}", resourceDo);
    	if(bindResult.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : bindResult.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        
        BaseResp resp = BaseResp.createSuccess(""); 	
    	
        JsonResult<List<ResourceQueryVO>> result = new JsonResult<List<ResourceQueryVO>>();

        result.setData(resourceService.queryResource());
        LogWorker.logEnd(log, "配置", "resourceVo:{}", resourceDo);
        return result.buildIsSuccess(true);
    }

    /**
     *
     * @param
     * @return
     */
    @RequestMapping("/queryTree")
    @ResponseBody
    public Object queryTree(@Valid AuthResourceDO resourceDo, BindingResult bindResult) {
    	
    	LogWorker.logStart(log, "配置", "resourceDo:{}", resourceDo);
    	if(bindResult.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : bindResult.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        
        BaseResp resp = BaseResp.createSuccess(""); 	
       
    	JsonResult<List<ResourceQueryVO>> result = new JsonResult<List<ResourceQueryVO>>();

        result.setData(resourceService.queryResource());
        LogWorker.logEnd(log, "配置", "resourceVo:{}", resourceDo);
        return result.buildIsSuccess(true);
    }
}
