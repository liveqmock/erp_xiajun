package com.wangqin.globalshop.web.controller.usercenter;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.web.dto.BaseDto;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	/**
	 * 用户登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String Login(@RequestParam("type") String type,
						@RequestParam(value = "mobileNo", required = false) String mobileNo,
						@RequestParam(value = "checkCode", required = false) String checkCode,
						@RequestParam(value = "thirdPartyId", required = false) String thirdPartyId,
						@RequestParam(value = "thirdPartyAvtar", required = false) String thirdPartyAvtar) {
		//TODO log
		JsonResult<AuthUserDO> result = new JsonResult<AuthUserDO>();
		JsonResult<List<AuthUserDO>> resultList = new JsonResult<List<AuthUserDO>>();

		List<AuthUserDO> responseUserList = null;
		if (StringUtils.isNotBlank(type) && type.equals("wechat")) {
			responseUserList = userService.selectUserByWxOpenId(thirdPartyId);
			//买手不存在
			if (null == responseUserList) {
				result.buildIsSuccess(false).buildMsg("用户不存在");
				return BaseDto.toString(result);
			}
			//买手只关联了一个公司
			if (1 == responseUserList.size()) {
				result.buildIsSuccess(true).buildData(responseUserList.get(0));
				return BaseDto.toString(result);
			}
			//TODO 多个company
			resultList.buildIsSuccess(true).buildData(responseUserList);
			return BaseDto.toString(resultList);
		} else {
			return "";
		}
	}
}
