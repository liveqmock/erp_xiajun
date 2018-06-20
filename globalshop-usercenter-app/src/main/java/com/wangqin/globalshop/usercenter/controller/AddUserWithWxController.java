package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.app.dal.dataVo.WxUserVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.usercenter.service.IUserRoleService;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.usercenter.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by 777 on 2018/6/20
 */
@Controller
@RequestMapping("/user")
public class AddUserWithWxController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleService userRoleService;

	@Autowired
	private QrCodeService qrCodeService;


	@RequestMapping("/addUserByqrcode")
	@ResponseBody
	public Object addUserByqrcode(String companyNo, WxUserVO wxUserVO) {


		JsonPageResult<String> result = new JsonPageResult<>();

		userService.addUserByqrcode(companyNo, wxUserVO);

		return result.buildIsSuccess(true);
	}

}
