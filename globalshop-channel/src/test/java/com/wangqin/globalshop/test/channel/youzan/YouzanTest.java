package com.wangqin.globalshop.test.channel.youzan;

import com.wangqin.globalshop.channel.dal.youzan.YouzanTradesSoldGet;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradesSoldGetParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * Create by 777 on 2018/6/7
 */
public class YouzanTest {

	private static final String appKey = "33606390583bfda06d";
	private static final String appSecret = "55b00672dade32bea53a629872c00f90";

	private static final String access_token = "48755c32f67930f5ab11f4860ada1e33";

	private static final String shopCode = "40661654";

	private DefaultYZClient yzClient = null;

	protected Logger logger = LogManager.getLogger(getClass());


	@Test
	public void getOrdersTest(){
		String startTime = "2018-05-02 10:33:00";
		String endTime = "2018-05-03 19:33:00";

				Date startUpdate = null;
				Date endUpdate = null;
				try {
					 startUpdate = DateUtil.convertStr2Date(startTime,DateUtil.formateStr19);
					 endUpdate = DateUtil.convertStr2Date(endTime,DateUtil.formateStr19);
				}catch (ParseException e){
					logger.info("");
				}

		// 方法
		YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();
		// 参数
		YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
		youzanTradesSoldGetParams.setType("ALL");
		// 买家已付款，等待发货
		youzanTradesSoldGetParams.setStatus("WAIT_SELLER_SEND_GOODS");
		youzanTradesSoldGetParams.setStartUpdate(startUpdate);
		youzanTradesSoldGetParams.setEndUpdate(endUpdate);
		youzanTradesSoldGetParams.setPageSize(100L);
		youzanTradesSoldGetParams.setUseHasNext(true);

		boolean hasNext = true;
		long pageNo = 1L;
		while (hasNext) {
			youzanTradesSoldGetParams.setPageNo(pageNo);
			youzanTradesSoldGet.setAPIParams(youzanTradesSoldGetParams);
			com.wangqin.globalshop.channel.dal.youzan.YouzanTradesSoldGetResult result = yzClient.invoke(youzanTradesSoldGet);

			// 设置循环
			pageNo++;
			hasNext = result.getHasNext();

			com.wangqin.globalshop.channel.dal.youzan.TradeDetailV2[] tradeList = result.getTrades();
			for (int i = tradeList.length - 1; i >= 0; i--) {
				logger.info(tradeList[i]);
			}
		}
	}
}
