 package com.wangqin.globalshop.item.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.JsonPageResult;

/**
 *
 * SiteMsg 控制层
 *
 */
@Controller
@RequestMapping("/sitemsg")
public class SiteMsgController extends BaseController {



//    @Autowired
//    private ISiteMsgService siteMsgService;
//   
//    @Autowired
//    private ISiteMsgReadService siteMsgReadService;
//    
//    @Autowired
//    private IUserService userService;s
   

    /**
     * 添加站内信，到site_msg和site_msg_read
     * 参数：content:内容，sendType:发送方式，receiverId：收件人，-1表示所有人
     * title:标题，status:状态，暂时需求没有说明确的定义
     * @author XiaJun
     */
//    @RequestMapping("/add")
//	@ResponseBody
//	public Object addToSiteMsg(SiteMsg siteMsg) {
//    	/**测试，这里面就是前端需要提供的所有字段**/
//    	siteMsg.setContent("群发内容测试");
//    	siteMsg.setSendType(0);
//    	siteMsg.setReceiverId(-1L);
//    	siteMsg.setTitle("群发标题测试");
//    	siteMsg.setStatus(0);
//    	/**测试，正式使用的时候请去掉测试代码**/
//    	siteMsg.setSenderId(this.getShiroUser().getId());//发件人
//    	siteMsg.setSendTime(new Date());//发送时间
//    	siteMsg.setGmtCreate(new Date());
//    	siteMsg.setGmtModified(new Date());
//    	//这里必须在代码里面设置主键，不然无法插入到site_msg_read表里面
//    	Long setId = siteMsgService.getMaxId()+1;
//    	siteMsg.setId(setId);
//    	//插入到site_msg
//    	Boolean flag = siteMsgService.insert(siteMsg);
//    	/**插入到site_msg_read，即发送站内信**/
//    	SiteMsgRead siteMsgRead = new SiteMsgRead();
//		siteMsgRead.setGmtCreate(new Date());
//		siteMsgRead.setGmtModified(new Date());
//		siteMsgRead.setIsDel(0);
//		siteMsgRead.setReadStatus(SiteMsgReadStatus.UnRead.getValue());
//		siteMsgRead.setSenderId(this.getShiroUser().getId());
//		siteMsgRead.setSiteMsgId(setId);
//    	//单发
//    	if(-1L != siteMsg.getReceiverId()) {
//    		siteMsgRead.setReceiverId(siteMsg.getReceiverId());//接受人
//    		siteMsgReadService.insert(siteMsgRead);
//    	} else {//群发
//    		List<SiteMsgRead> readList = new ArrayList<SiteMsgRead>();
//    		for(Long id:userService.selectUserIds()) {//发给所有人
//    			siteMsgRead.setReceiverId(id);
//        		siteMsgReadService.insert(siteMsgRead);
//    		}
//    	}
//    	JsonResult<SiteMsg> result = new JsonResult<SiteMsg>();
//    	if(flag) {
//    		result.buildMsg("添加站内信成功");
//        	result.buildIsSuccess(true);
//    	} else {
//    		result.buildMsg("添加站内信失败");
//        	result.buildIsSuccess(false);
//    	}
//    	return result;
//	}
    
    /**
     * @author XiaJun
     * 分页读取site_msg_read表，需要提供阅读状态read_status和删除状态is_del和发件人Id和分页信息,-1不限制状态
     */
    @RequestMapping("/queryUserSiteMsg")
	@ResponseBody
	public Object queryUserSiteMsg() {
//    	/*****测试******/
//    	siteMsgQueryVO.setFirstStart(0);
//    	siteMsgQueryVO.setPageIndex(0);
//    	siteMsgQueryVO.setPageSize(10);
//    	siteMsgQueryVO.setIsDel(-1);
//    	siteMsgQueryVO.setReadStatus(-1);
//    	siteMsgQueryVO.setSenderId(-1L);
//    	/*****测试******/
//    	siteMsgQueryVO.setUserId(this.getShiroUser().getId());
//		JsonPageResult<List<SiteMsgRead>> result  = siteMsgReadService.queryUserMsg(siteMsgQueryVO);
//		if(0 == result.getData().size()) {
//			result.buildMsg("无相关状态站内信");
//			result.buildIsSuccess(false);
//		} else {
//			result.buildMsg("查找成功");
//			result.buildIsSuccess(true);
//		}
    	JsonPageResult<String> result = new JsonPageResult<>();
		//result.buildMsg("无相关状态站内信");
		result.buildIsSuccess(true);
		return result;
	}
    
//    /**
//     * 分页读取site_msg表，需要提供send_type和status和分页信息,-1不限制状态
//     * @author XiaJun
//     */
//    @RequestMapping("/querySiteMsg")
//	@ResponseBody
//	public Object querySiteMsg(SiteMsgQueryVO siteMsgQueryVO) {
//    	/*****测试******/
//    	siteMsgQueryVO.setFirstStart(0);
//    	siteMsgQueryVO.setPageIndex(0);
//    	siteMsgQueryVO.setPageSize(10);
//    	siteMsgQueryVO.setSendType(1);
//    	siteMsgQueryVO.setStatus(1);
//    	/*****测试******/
//    	JsonPageResult<List<SiteMsg>> result  = siteMsgService.queryMsgByStatusSendType(siteMsgQueryVO);
//    	if(0 == result.getData().size()) {
//    		result.buildMsg("无相关状态站内信");
//    		result.buildIsSuccess(false);
//    	} else {
//    		result.buildMsg("查找成功");
//    		result.buildIsSuccess(true);
//    	}
//    	return result;
//    	
//	}
//    
//    /**
//     * 更新当前用户信件为已读状态
//     * @author XiaJun
//     */
//    @RequestMapping("/readMsg")
//	@ResponseBody
//	public Object readMsg() {
//    	JsonResult<SiteMsgRead> result = new JsonResult<SiteMsgRead>();
//    	siteMsgReadService.updateUserMsgStatus(SiteMsgReadStatus.Read.getValue(), this.getShiroUser().getId());
//    	result.buildMsg("更新成功");
//    	result.buildIsSuccess(true);
//    	return result;
//	}
}