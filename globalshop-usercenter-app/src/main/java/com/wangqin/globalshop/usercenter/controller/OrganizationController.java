	package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.api.dto.response.BaseResp;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthOrganizationDO;
import com.wangqin.globalshop.biz1.app.vo.OrganizationQueryVO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.LogWorker;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.usercenter.service.IOrganizationService;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @description：部门管理
 */
@Controller
@RequestMapping("/organization")
@Authenticated
public class OrganizationController extends BaseController {
	
	protected static Logger log = LoggerFactory.getLogger("System");
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
    public Object tree(@Valid AuthOrganizationDO organizationDo, BindingResult result) {
    		LogWorker.logStart(log, "配置", "organizationDo:{}", organizationDo);
    		
    		if(result.hasErrors()) {
            	StringBuffer sb = new StringBuffer();
            	for(ObjectError error : result.getAllErrors()) {
            		sb.append(error.getDefaultMessage()).append(",");
            	}
            	return BaseResp.createFailure(sb.toString());
            }
            BaseResp resp = BaseResp.createSuccess("");
    		
    		LogWorker.logEnd(log, "配置", "organizationDo:{}", organizationDo);
            return organizationService.selectTree();
    }

    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid(@Valid AuthOrganizationDO organizationDo, BindingResult result) {
    	LogWorker.logStart(log, "配置", "organizationDo:{}", organizationDo);
		
		if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
		
		LogWorker.logEnd(log, "配置", "organizationDo:{}", organizationDo);
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
        if(EasyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo())) {
            return renderError("请登录后查询");
        }
        if(StringUtil.isBlank(authOrganizationDO.getName())) {
            return renderError("资源名称不能为空");
        }
        if(EasyUtil.isStringEmpty(authOrganizationDO.getSeq().toString())) {
            return renderError("排序不能为空");
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
    public Object edit(@Valid OrganizationQueryVO organizationVo, AuthOrganizationDO organization, BindingResult result) {
    	LogWorker.logStart(log, "配置", "organizetionVo:{}", organizationVo);
    	if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
        organizationVo.setOrgId(organizationVo.getCode());
        organizationService.updateByOrganizationVo(organizationVo);
        LogWorker.logEnd(log, "配置", "organizationVo:{}", organizationVo);
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
    public Object delete(Long id, @Valid AuthOrganizationDO organizationDo, BindingResult result) {
    	LogWorker.logStart(log, "配置", "organizationDo:{}", organizationDo);
		
		if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
		
    	organizationService.deleteById(id);
        
    	LogWorker.logEnd(log, "配置", "organizationDo:{}", organizationDo);
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
    public Object queryList(OrganizationQueryVO organizationQueryVO, @Valid AuthOrganizationDO organizationDo, BindingResult bindResult) {
    	LogWorker.logStart(log, "配置", "organizationDo:{}", organizationDo);
		
		if(bindResult.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : bindResult.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
		
    	String companyNo = AppUtil.getLoginUserCompanyNo();
    	
        JsonPageResult<List<AuthOrganizationDO>> result = organizationService.queryOrganizationList(companyNo);
        LogWorker.logEnd(log, "配置", "organizationDo:{}", organizationDo);
        return result.buildIsSuccess(true);
    }
}
