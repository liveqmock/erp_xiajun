package com.wangqin.globalshop.biz1.app.task;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.biz1.app.service.channel.ChannelFactory;
import com.wangqin.globalshop.biz1.app.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.biz1.app.service.company.ICompanyService;
import com.wangqin.globalshop.common.utils.EasyUtil;

/**
 * 自动去有赞下载订单
 * 
 * @author LiuYang
 *
 */
@Component
public class AutoYouzanTradesSoldGetTask {

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired ICompanyService companyService;

	@Autowired IChannelAccountService channelAccountService;
	
	//每隔半小时执行一次
	@Scheduled(cron = "0 0/30 * * * ?")
	public void run(){
		logger.info("定时任务：自动去有赞下载订单===>Start");
		//首先轮训出company
		CompanyDO companySo = new CompanyDO();
		companySo.setIsDel(false);
		List<CompanyDO>  companyDOList = companyService.queryPoList(companySo);
		if(EasyUtil.isListEmpty(companyDOList)){
             return;
		}

		//第二步，轮训出该账户下的有赞账户token等
		for(CompanyDO company : companyDOList){
			ChannelAccountSo so = new ChannelAccountSo();
			so.setType(ChannelType.YouZan.getValue());
			so.setCompanyNo(company.getCompanyNo());
			so.setStatus(0);
			List<ChannelAccountDO> channelAccountList = channelAccountService.queryPoList(so);
			if(EasyUtil.isListEmpty(channelAccountList)){
				break;
			}
			for(ChannelAccountDO channelAccountDO : channelAccountList){
				try {
					ChannelFactory.getChannel(channelAccountDO).syncOrder();
				} catch (Exception e) {
					logger.error("get youzan orders error, shopCode: "+channelAccountDO.getShopCode(),e);
				}
			}
		}
		logger.info("定时任务：自动去有赞下载订单===>End");
	}
}
