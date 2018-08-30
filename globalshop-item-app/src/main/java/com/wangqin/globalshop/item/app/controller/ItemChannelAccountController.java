package com.wangqin.globalshop.item.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.IItemChannelAccountService;

/**
 * 商品发布：可用的渠道
 * @author xiajun
 *
 */
@Authenticated
@Controller
public class ItemChannelAccountController {

    //旧的service
	@Autowired
	private IItemChannelAccountService itemChannelAccountService;
	
	
	//新的service
//	@Autowired
//	private ItemChannelAccountFeignService itemChannelAccountService;
	
	/**
	 * 获取当前用户所在公司可用的渠道类型
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/channelAccount/querylist")
	@ResponseBody
	public Object query() {		
		JsonResult<List<ChannelInfo>> result = new JsonResult<>();
		if(!loginCheck()) {
        	return result.buildIsSuccess(false).buildMsg("请先登录");
        } 
		List<ChannelInfo> channelList = new ArrayList<ChannelInfo>();
		List<String> channelNoList = itemChannelAccountService.queryChannelNoByCompanyNo(AppUtil.getLoginUserCompanyNo());	
		for (String no:channelNoList) {
			ChannelInfo channelInfo = new ChannelInfo();
			channelInfo.setChannelNo(no);
			channelInfo.setChannelName(ChannelType.getChannelName(Integer.parseInt(no)));
			channelList.add(channelInfo);
		}
		result.setData(channelList);
		return result.buildIsSuccess(true);
	}
	
	private class ChannelInfo {
		private String channelNo;
		private String channelName;
		public String getChannelNo() {
			return channelNo;
		}
		public void setChannelNo(String channelNo) {
			this.channelNo = channelNo;
		}
		public String getChannelName() {
			return channelName;
		}
		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}		
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
