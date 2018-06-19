package com.wangqin.globalshop.wen.controller.usercenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.usercenter.service.IUserRoleService;
import com.wangqin.globalshop.usercenter.service.IUserService;

/**
 * 一键分享-用户接口
 * @author admin
 *
 */
@Controller
@RequestMapping("/share/user")
public class ShareUserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleService userRoleService;
	/**
	 * 用户登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Object Login(AuthUserDO user) {
		JsonResult<AuthUserDO> result = new JsonResult<AuthUserDO>();
		AuthUserDO responseUser = userService.selectUserByWxOpenId(user.getWxOpenId());
//		AuthUserRoleDO authUserRoleDO = userRoleService.selectRoleIdByUserId(responseUser.getId());
		if(null == responseUser) {
			result.buildIsSuccess(false).buildMsg("无该用户");
		}
		result.buildIsSuccess(true).buildData(responseUser);
		
		return result;
	}
}
