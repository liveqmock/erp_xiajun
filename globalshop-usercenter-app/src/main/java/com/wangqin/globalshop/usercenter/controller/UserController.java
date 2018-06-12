package com.wangqin.globalshop.usercenter.controller;

import com.gargoylesoftware.htmlunit.javascript.host.event.UserProximityEvent;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.vo.UserQueryVO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.usercenter.service.IUserRoleService;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.usercenter.vo.UserVo;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @description：用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
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
    public Object dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
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
    public Object add(UserVo userVo) {
    	
        AuthUserDO authUserLoginName = userService.selectByLoginName(userVo.getLoginName());
        if (authUserLoginName != null ) {
            return renderError("用户名已存在!");
        }
        String userNo=DateUtil.formatDate(new Date(),"yyMMdd HH:mm:ss")+String.format("%1$06d", RandomUtils.nextInt(1000000));

        userVo.setUserNo(userNo);
        userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        userService.insertByVo(userVo);
        
        AuthUserDO authUser = userService.selectUserVoByUserNo(userNo);
        System.out.println(authUser.getId());
        
        
        userVo.setId(authUser.getId());
        userService.insertByUserVo(userVo);
        
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
    public Object update(UserVo userVo) {
        AuthUserDO list = userService.selectByLoginName(userVo.getLoginName());
        if (list != null ) {
            return renderError("用户名已存在!");
        }
        String userNo=DateUtil.formatDate(new Date(),"yyMMdd HH:mm:ss")+String.format("%1$06d", RandomUtils.nextInt(1000000));
        userVo.setUserNo(userNo);
        userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        userService.updateByVo(userVo);
        return renderSuccess("添加成功");
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
    public Object edit(UserVo userVo) {
        AuthUserDO list = userService.selectByLoginName(userVo.getLoginName());
        if (list == null ) {
            return renderError("用户不存在!");
        }
        if (StringUtils.isNotBlank(userVo.getPassword())) {
            userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        }
        userService.updateByVo(userVo);
        return renderSuccess("修改成功！");
    }

    /**
     * 修改密码页
     *
     * @return
     */
    @GetMapping("/editPwdPage")
    public String editPwdPage() {
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
    public Object editUserPwd(String oldPwd, String pwd) {
        AuthUserDO user = userService.selectByLoginName(AppUtil.getLoginUserId());
        if (user ==null ) {
            return renderError("该用户不存在!");
        }
        if (!user.getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
            return renderError("老密码不正确!");
        }

        userService.changePasswordByLoginName(AppUtil.getLoginUserId(), DigestUtils.md5Hex(pwd));
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
    public Object delete(Long id) {
        userService.deleteUserById(id);
        userRoleService.deleteUserRoleByUserId(id);
      
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
    public Object queryList(UserQueryVO userQueryVO) {
        JsonPageResult<List<UserQueryVO>> result = userService.queryUserQueryVOList(userQueryVO);
        return result.buildIsSuccess(true);
    }
}
