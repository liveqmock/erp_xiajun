package com.wangqin.globalshop.web.controller.usercenter;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.common.base.BaseDto;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
						@RequestParam(value = "thirdPartyUnionid", required = false) String thirdPartyUnionid,
						@RequestParam(value = "thirdPartyAvtar", required = false) String thirdPartyAvtar) {
		JsonResult<AuthUserDO> result = new JsonResult<AuthUserDO>();

		List<AuthUserDO> responseUserList = null;
		//TODO enum wechat mobile
		if (StringUtils.isNotBlank(type) && "wechat".equals(type)) {
			//微信登录
			responseUserList = userService.selectUserByWxUnionId(thirdPartyUnionid);
			//买手不存在
			if (CollectionUtils.isEmpty(responseUserList)) {
				result.buildIsSuccess(false).buildMsg("用户不存在");
				return BaseDto.toString(result);
			}
			//买手只关联了一个公司
			if (1 == responseUserList.size()) {
				result.buildIsSuccess(true).buildData(responseUserList.get(0));
				return BaseDto.toString(result);
			}
			//TODO 多个company
			result.buildIsSuccess(true).buildData(responseUserList.get(0));
			return BaseDto.toString(result);
		} else {
			//TODO hard code
			if ("18366116306".equals(mobileNo) && "147258".equals(checkCode)) {
				AuthUserDO authUserDO = userService.selectUserByPhone("18366116306");
				result.buildIsSuccess(true).buildData(authUserDO);
				return BaseDto.toString(result);
			} else {
				result.buildIsSuccess(false).buildMsg("用户不存在");
				return BaseDto.toString(result);
			}
		}
	}

}
