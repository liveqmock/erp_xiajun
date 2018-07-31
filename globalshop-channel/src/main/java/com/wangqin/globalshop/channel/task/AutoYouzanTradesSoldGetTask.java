package com.wangqin.globalshop.channel.task;

import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ChannelAccountSo;
import com.wangqin.globalshop.channel.service.channel.ChannelFactory;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channel.service.company.ICompanyService;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自动去有赞下载订单
 * 
 * @author LiuYang
 */
@Component
public class AutoYouzanTradesSoldGetTask {

    protected Logger       logger = LogManager.getLogger(getClass());

    @Autowired
    ICompanyService        companyService;

    @Autowired
    IChannelAccountService channelAccountService;

    // 每隔半小时执行一次
    @Scheduled(cron = "0 0/30 * * * ?")
    public void run() {
        logger.info("定时任务：自动去有赞下载订单===>Start");
        Long startTime = System.currentTimeMillis();
        // 首先轮训出company
        CompanyDO companySo = new CompanyDO();
        companySo.setIsDel(false);
        List<CompanyDO> companyDOList = companyService.queryPoList(companySo);

        Long shopCount= 0L;

        // 第二步，轮训出该账户下的有赞账户token等
        for (CompanyDO company : companyDOList) {
            ChannelAccountSo so = new ChannelAccountSo();
            so.setType(ChannelType.YouZan.getValue());
            so.setCompanyNo(company.getCompanyNo());
            so.setStatus(0);
            List<ChannelAccountDO> channelAccountList = channelAccountService.queryPoList(so);
            if (EasyUtil.isListEmpty(channelAccountList)) {
                break;
            }
            for (ChannelAccountDO channelAccountDO : channelAccountList) {
                try {
                    ChannelFactory.getChannel(channelAccountDO).syncOrder();
                    shopCount++;
                } catch (Exception e) {
                    logger.error("get youzan orders error, shopCode: " + channelAccountDO.getShopCode(), e);
                }
            }
        }
        Long endTime = System.currentTimeMillis();
        logger.info("定时任务：自动去有赞下载订单===>End, use time:" + (endTime - startTime) +" ms. shopCount: " + shopCount);
    }
}
