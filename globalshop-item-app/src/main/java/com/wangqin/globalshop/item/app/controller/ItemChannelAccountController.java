package com.wangqin.globalshop.item.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;

import com.wangqin.globalshop.biz1.app.vo.ChannelAccountVO;
import com.wangqin.globalshop.common.shiro.ShiroUser;
import com.wangqin.globalshop.common.shiro.ShiroUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.controller.ItemChannelAccountController.ChannelInfo;
import com.wangqin.globalshop.item.app.service.IChannelAccountServ;

@Controller
public class ItemChannelAccountController {

	@Autowired
	private IChannelAccountServ channelAccountServ;
	
	static class ChannelInfo {
		private Integer type;
		private String name;
		private Integer status;
		
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
	}
	
	/**
	 * 获取当前用户可用的渠道类型
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/channelAccount/querylist")
	@ResponseBody
	public Object query() {
		
		JsonResult<List<ChannelInfo>> result = new JsonResult<>();
		ChannelAccountVO channelAccountVO = new ChannelAccountVO();
		//ShiroUser user = ShiroUtil.getShiroUser();
		//if (user == null) {
		//	return result.buildIsSuccess(false);
		//}

		channelAccountVO.setCompanyNo("1");//temp
		List<ChannelAccountDO> accountList = channelAccountServ.queryChannelAccountList(channelAccountVO);
		List<ChannelInfo> list = new ArrayList<ChannelInfo>();
		accountList.forEach((account) -> {
			ChannelInfo vo = new ChannelInfo();
			vo.setType(account.getType());
			vo.setName(account.getChannelName());
			vo.setStatus(account.getStatus());
			list.add(vo);
		});
		
		result.setData(list);
		return result.buildIsSuccess(true);
	}
}
