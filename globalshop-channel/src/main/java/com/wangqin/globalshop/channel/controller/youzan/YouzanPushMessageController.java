package com.wangqin.globalshop.channel.controller.youzan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.channel.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.channel.dal.youzan.YouzanMsgPushEntity;
import com.wangqin.globalshop.channel.service.channel.ChannelFactory;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.common.base.BaseController;

import net.sf.json.JSONObject;

/**
 * YouzanPushMessageController
 * 推送后我们再去抓
 * 推送服务消息接收示例
 * 依赖SPRING 3.0或以上版本
 * @auther ChengZi
 * @data 16/9/10
 */
@Controller
@RequestMapping("/youzanPushMessage")
public class YouzanPushMessageController extends BaseController {

	@Autowired IChannelAccountService channelAccountService;
    
    @RequestMapping(value = "/pullOrder", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object pullOrder(@RequestBody YouzanMsgPushEntity entity) {
    	logger.error("接收有赞推送消息：" + entity.toString());
    	// 查出channel_account
    	ChannelAccountSo tmEntity = new ChannelAccountSo();
		tmEntity.setType(ChannelType.YouZan.getValue());
		tmEntity.setAppKey(entity.getClient_id());
		tmEntity.setShopCode(entity.getKdt_id()+"");//店铺ID
		ChannelAccountDO selectEntity = channelAccountService.queryPo(tmEntity);
    	JSONObject res;
		try {
			res = (JSONObject) ChannelFactory.getChannel(selectEntity).syncOrder(entity);
		} catch (Exception e) {
			logger.error("接收有赞推送消息 异常：", e);
			res = new JSONObject();
	        res.put("code", 0);
	        res.put("msg", "success");
		}
        return res;
    }
}
