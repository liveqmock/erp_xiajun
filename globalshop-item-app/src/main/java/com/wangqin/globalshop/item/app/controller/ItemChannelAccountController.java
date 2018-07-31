package com.wangqin.globalshop.item.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.common.utils.JsonResult;

import com.wangqin.globalshop.item.app.service.IItemChannelAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Authenticated
@Controller
public class ItemChannelAccountController {

	@Autowired
	private IItemChannelAccountService itemChannelAccountService;
	
//	static class ChannelInfo {
//		private Integer type;
//		private String name;
//		private Integer status;
//		
//		public Integer getType() {
//			return type;
//		}
//		public void setType(Integer type) {
//			this.type = type;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public Integer getStatus() {
//			return status;
//		}
//		public void setStatus(Integer status) {
//			this.status = status;
//		}
//	}
//	
//	/**
//	 * 获取当前用户可用的渠道类型
//	 *
//	 * @param
//	 * @return
//	 */
//	@RequestMapping("/channelAccount/querylist")
//	@ResponseBody
//	public Object query() {		
//		JsonResult<List<ChannelInfo>> result = new JsonResult<>();
//		if(null == AppUtil.getLoginUserCompanyNo()) {
//			return result.buildIsSuccess(false).buildMsg("请先登录");
//		}
//		ChannelAccountVO channelAccountVO = new ChannelAccountVO();
//		channelAccountVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());//temp
//		List<ChannelAccountDO> accountList = itemChannelAccountService.queryChannelAccountList(channelAccountVO);
//		List<ChannelInfo> list = new ArrayList<ChannelInfo>();
//		
//		if(!EasyUtil.isListEmpty(accountList)) {
//			accountList.forEach((account) -> {
//				ChannelInfo vo = new ChannelInfo();
//				vo.setType(account.getType());
//				vo.setName(account.getChannelName());
//				vo.setStatus(account.getStatus());
//				list.add(vo);
//			});
//		}		
//		
//		result.setData(list);
//		return result.buildIsSuccess(true);
//	}
	
	/**
	 * 获取当前用户所在公司可用的渠道类型
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/channelAccount/querylist")
	@ResponseBody
	public Object query() {		
		JsonResult<List<ChannelAccountDO>> result = new JsonResult<>();
		if(!loginCheck()) {
        	return result.buildIsSuccess(false).buildMsg("请先登录");
        } 
		List<ChannelAccountDO> channelList = itemChannelAccountService.queryChannelAccountListByCompanyNo(AppUtil.getLoginUserCompanyNo());			
		result.setData(channelList);
		return result.buildIsSuccess(true);
	}
	
	 /**
     * 工具类
     * 用户登录判断
     * @param itemCode
     * @return
     */
    public Boolean loginCheck() {
    	if(IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo()) || IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserId())) {
         	return false;
        }  
    	return true;
    }
	
}
