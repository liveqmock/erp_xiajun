package com.wangqin.globalshop.channel.task;

import com.taobao.api.Constants;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.request.CookieRefreshRequest;
import com.taobao.api.response.CookieRefreshResponse;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoTaobaoRefreshCookieTask {

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired IChannelAccountService channelAccountService;

	@Scheduled(cron = "0 0/15 * * * ?")
	public void refreshCookie() {
		logger.info("refresh taobao token start");

		ChannelAccountSo so = new ChannelAccountSo();
		so.setType(ChannelType.TaoBao.getValue());
		
		List<ChannelAccountDO> channelAccountList = channelAccountService.queryPoList(so);
        if(channelAccountList != null) {
        	for(ChannelAccountDO po : channelAccountList) {
        		doRefreshCookie(po);
        	}
        }		
		logger.info("refresh taobao token end");
	}

	
	private void doRefreshCookie(ChannelAccountDO po) {
		if(po == null) {
			return;
		}
		CookieRefreshRequest refreshRequest = new CookieRefreshRequest();
		DefaultTaobaoClient tbClient = new DefaultTaobaoClient();
		refreshRequest.setShopType(Constants.SHOP_TYPE_TAOBAO);
		try {
			CookieRefreshResponse refreshResponse = tbClient.execute(refreshRequest, po.getCookie());
			logger.info("成功："+refreshResponse.getBody());
		} catch (Exception e) {
			logger.error("refresh taobao token error", e);
		}
	}
}
