package com.wangqin.globalshop.channel.task;

import com.wangqin.globalshop.common.utils.ExchangeRateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 获取实时汇率
 * @author 777
 */
@Component
public class AutoGetExchangeRateTask {
	protected Logger logger = LogManager.getLogger(getClass());

	@Scheduled(cron = "0 15 17 ? * *")
    public void getAccessToken() {
		logger.error("获取欧元港元汇率");
		ExchangeRateUtil.getExchangeRate();
    }
}
