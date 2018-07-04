package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.api.dto.response.BaseResp;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.usercenter.service.IUserRoleService;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.usercenter.service.QrCodeService;
import com.wangqin.globalshop.usercenter.vo.UserVo;

import lombok.val;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * @description：用户管理
 */
@Controller
@RequestMapping("/user")
@Authenticated
public class UserController extends BaseController {
	
	protected static Logger log = LoggerFactory.getLogger("System");
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private QrCodeService qrCodeService;
    /**
     * 用户管理页
     *
     * @return
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/user";
    }

    /**
     * 用户管理列表
     *
     * @param userVo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @PostMapping("/dataGrid")
    @ResponseBody
    public Object dataGrid( UserVo userVo, Integer page, Integer rows, String sort, String order, @Valid AuthUserDO userDo, BindingResult result) {
    	LogWorker.logStart(log, "配置", "userVo:{}", userVo);
    	if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
    	BaseResp resp = BaseResp.createSuccess("");
    	rows = 1000;
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(userVo.getName())) {
            condition.put("name", userVo.getName());
        }
        if (userVo.getOrganizationId() != null) {
            condition.put("organizationId", userVo.getOrganizationId());
        }
        if (userVo.getCreatedateStart() != null) {
            condition.put("startTime", userVo.getCreatedateStart());
        }
        if (userVo.getCreatedateEnd() != null) {
            condition.put("endTime", userVo.getCreatedateEnd());
        }
        pageInfo.setCondition(condition);
        userService.selectDataGrid(pageInfo);
        LogWorker.logEnd(log, "配置", "userVo:{}", userVo);
        return pageInfo;
    }

    /**
     * 添加用户页
     *
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/userAdd";
    }

    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object add(@Valid UserVo userVo, BindingResult result) {
    	LogWorker.logStart(log, "配置", "userVo:{}", userVo);
    	if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
    	BaseResp resp = BaseResp.createSuccess("");
    	String userNo=CodeGenUtil.genUserNo();
        userVo.setUserNo(userNo);
        userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        
        
        
        AuthUserDO authUserLoginName = userService.selectByLoginName(userVo.getLoginName());
        if (authUserLoginName != null ) {
            return renderError("用户名已存在!");
        }
        
        userService.insertByVo(userVo);
        
        UserQueryVO userQueryVo = userService.selectUserVoByUserNo(userNo);
 
        userVo.setId(userQueryVo.getId());
        userService.insertByUserVo(userVo);
        
        LogWorker.logEnd(log, "配置", "userVo:{}", userVo);
        return renderSuccess("添加成功");
    }
    /**
     * 修改用户信息
     *
     * @param userVo
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object update(@Valid UserVo userVo, BindingResult result) {
    	LogWorker.logStart(log, "配置", "userVo{}", userVo);
    	if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
    	
        BaseResp resp = BaseResp.createSuccess("");
    	String userNo=CodeGenUtil.genUserNo();
        userVo.setUserNo(userNo);
        userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        
        AuthUserDO authUserLoginName = userService.selectByLoginName(userVo.getLoginName());
        if (authUserLoginName != null ) {
            return renderError("用户名已存在!");
        }
        
        userService.updateByVo(userVo);
        userRoleService.deleteUserRoleByUserId(userVo.getId());
        userService.insertByUserVo(userVo);
        
        LogWorker.logEnd(log, "配置", "userVo{}", userVo);
        
        return renderSuccess("修改成功");
    }

    /**
     * 编辑用户页
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Long id, Model model) {
        String loginName=AppUtil.getLoginUserId();
        UserVo userVo = userService.selectVoById(loginName);
        List<AuthRoleDO> rolesList = userVo.getRolesList();
        List<Long> ids = new ArrayList<Long>();
        for (AuthRoleDO role : rolesList) {
            ids.add(role.getId());
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        return "admin/userEdit";
    }

    /**
     * 编辑用户
     *
     * @param userVo
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object edit(@Valid UserVo userVo, BindingResult result) {
        LogWorker.logStart(log, "配置", "userVo{}", userVo);
    	
    	AuthUserDO list = userService.selectByLoginName(userVo.getLoginName());
        if (list == null ) {
            return renderError("用户不存在!");
        }
        if (StringUtils.isNotBlank(userVo.getPassword())) {
            userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        }
        
        if(result.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : result.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
        
        userService.updateByVo(userVo);
        
        LogWorker.logEnd(log, "配置", "userVo{}", userVo);
        
        return renderSuccess("修改成功！");
    }

    /**
     * 修改密码页
     *
     * @return
     */
    @GetMapping("/editPwdPage")
    public String editPwdPage(@Valid AuthUserDO userDo, BindingResult bindResult) {
        
    	return "admin/userEditPwd";
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param pwd
     * @return
     */
    @RequestMapping("/editUserPwd")
    @ResponseBody
    public Object editUserPwd(String oldPwd, String pwd, @Valid AuthUserDO userDo, BindingResult bindResult) {
    	LogWorker.logStart(log, "配置", "userVo:{}", userDo);
    	
    	if(bindResult.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : bindResult.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
    	AuthUserDO user = userService.selectByLoginName(AppUtil.getLoginUserId());
        if (user ==null ) {
            return renderError("该用户不存在!");
        }
        if (!user.getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
            return renderError("老密码不正确!");
        }

        userService.changePasswordByLoginName(AppUtil.getLoginUserId(), DigestUtils.md5Hex(pwd));
        LogWorker.logEnd(log, "配置", "userDo:{}", userDo);
        return renderSuccess("密码修改成功！");
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id, @Valid AuthUserDO userDo, BindingResult bindResult) {
    	LogWorker.logStart(log, "配置", "userVo:{}", userDo);
    	
    	if(bindResult.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : bindResult.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
    	userService.deleteUserById(id);
        userRoleService.deleteUserRoleByUserId(id);
        LogWorker.logEnd(log, "配置", "userVo:{}", userDo);
        return renderSuccess("删除成功！");
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object query(Long id) {
        JsonResult<UserQueryVO> result = new JsonResult<>();

        return result.buildData(userService.queryVoById(id)).buildIsSuccess(true);
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public Object queryList(@Valid AuthUserDO userDo, BindingResult bindResult) {
    	LogWorker.logStart(log, "配置", "userVo:{}", userDo);
    	
    	if(bindResult.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : bindResult.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
        
    	String companyNo = AppUtil.getLoginUserCompanyNo();
        
    	JsonResult<List<AuthUserDO>> result = new JsonResult<>();
    	
    	List<AuthUserDO> userList = userService.queryUserByCompanyNo(companyNo);
    	
    	result.setData(userList);
    	LogWorker.logEnd(log, "配置", "userVo:{}", userDo);
        return result.buildIsSuccess(true);
    }


    @RequestMapping("/getqrcode")
    @ResponseBody
    public Object getQrcode(@Valid AuthUserDO userDo, BindingResult bindResult) {
    	LogWorker.logStart(log, "配置", "userVo:{}", userDo);    	
    	if(bindResult.hasErrors()) {
        	StringBuffer sb = new StringBuffer();
        	for(ObjectError error : bindResult.getAllErrors()) {
        		sb.append(error.getDefaultMessage()).append(",");
        	}
        	return BaseResp.createFailure(sb.toString());
        }
        BaseResp resp = BaseResp.createSuccess("");
    	JsonPageResult<String> result = new JsonPageResult<>();

        String qrCodeUrl = qrCodeService.getQrCodeUrl(AppUtil.getLoginUserCompanyNo());

        if(EasyUtil.isStringEmpty(qrCodeUrl)){
            return result.buildIsSuccess(false).buildMsg("生成二维码失败");
        }

        result.setData(qrCodeUrl);
        LogWorker.logEnd(log, "配置", "userVo:{}", userDo);
        return result.buildIsSuccess(true);
    }




}
