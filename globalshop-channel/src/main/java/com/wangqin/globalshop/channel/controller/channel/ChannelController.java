package com.wangqin.globalshop.channel.controller.channel;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelDO;
import com.wangqin.globalshop.channel.service.channel.IChannelOfflineService;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/channel")
@Authenticated
public class ChannelController extends BaseController {


    @Autowired
	private IChannelOfflineService channelOfflineService;

    /**
     * 获取当前用户可用的渠道类型
     *
     * @param
     * @return
     */
    @RequestMapping("/querylist")
    @ResponseBody
    public Object query() {

        JsonResult<List<ChannelDO>> result = new JsonResult<>();
        List<ChannelDO> accountList = channelOfflineService.queryChannelList();

        result.setData(accountList);
        return result.buildIsSuccess(true);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(ChannelDO channel) {
        JsonResult<String> result = new JsonResult<>();

		channel.setChannelNo(channel.getName());
		channel.setIsDel(false);
		channel.setGmtCreate(new Date());
		channel.setGmtModify(new Date());
		channel.setCreator(AppUtil.getLoginUserId());
		channel.setModifier(AppUtil.getLoginUserId());

        int ret = channelOfflineService.insert(channel);

        return result.buildIsSuccess(ret == 1);
    }

	@RequestMapping("/update")
	@ResponseBody
	public Object update(ChannelDO channel) {
		JsonResult<String> result = new JsonResult<>();

		channel.setChannelNo(channel.getName());
		channel.setIsDel(false);
		channel.setGmtCreate(new Date());
		channel.setGmtModify(new Date());
		channel.setCreator(AppUtil.getLoginUserId());
		channel.setModifier(AppUtil.getLoginUserId());

        int ret = channelOfflineService.updateByPrimaryKey(channel);

        return result.buildIsSuccess(ret==1);
	}
	
	
	@RequestMapping("/query")
    @ResponseBody
    public Object querySingle(Long id) {
        JsonResult<ChannelDO> result = new JsonResult<>();
        ChannelDO channel = channelOfflineService.selectByPrimaryKey(id);
        result.setData(channel);
        return result.buildIsSuccess(true);
    }
	
	@RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        JsonResult<Object> result = new JsonResult<>();
        int flag = channelOfflineService.deleteByPrimaryKey(id);
        if(flag == 1) {
            return  result.buildIsSuccess(true);
        }
        return result.buildIsSuccess(false);
    }
}
