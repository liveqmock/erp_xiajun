package com.wangqin.globalshop.channel.task;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.channel.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;

@Component
public class AutoTaobaoTradesSoldGetTask {

    protected Logger logger = LogManager.getLogger(getClass());
		
    @Autowired IChannelAccountService channelAccountService;
    
	//每隔半小时执行一次
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run(){		
		Long startTime = System.currentTimeMillis();		
		logger.info("定时任务：自动去淘宝下载订单===>Start");				
		ChannelAccountSo so = new ChannelAccountSo();
		so.setType(ChannelType.TaoBao.getValue());
		List<ChannelAccountDO> channelAccountList = channelAccountService.queryPoList(so);
        if(channelAccountList != null) {
        	for(ChannelAccountDO channelAccount : channelAccountList) {
        		try {
        			//ChannelFactory.getChannel(channelAccount).syncOrder();
        		} catch (Exception e) {			
        			logger.error("",e);
        		}        		      		
        	}
        }										
		Long endTime = System.currentTimeMillis();
		logger.info("定时任务：自动去淘宝下载订单===>End,time: "+(endTime-startTime) +" ms");		
	}

}
