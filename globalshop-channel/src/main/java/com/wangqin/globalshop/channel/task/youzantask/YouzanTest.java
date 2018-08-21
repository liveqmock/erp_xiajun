package com.wangqin.globalshop.channel.task.youzantask;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.LogisticCompanyDOMapperExt;
import com.wangqin.globalshop.common.base.BaseDto;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanLogisticsExpressGet;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanLogisticsExpressGetParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanLogisticsExpressGetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by 777 on 2018/8/20
 */
public class YouzanTest {

	private static Logger logger = LoggerFactory.getLogger("YouzanService");

	private static final String access_token = "ed6c4dba332b3a308f166933e21b0ad3";//

	@Autowired
	private LogisticCompanyDOMapperExt logisticCompanyDOMapperExt;


	public static void main(String[] args) {
		getYzExpress();
	}



	public static void getYzExpress(){

		YZClient client = new DefaultYZClient(new Token(access_token)); //new Sign(appKey, appSecret)
		YouzanLogisticsExpressGetParams youzanLogisticsExpressGetParams = new YouzanLogisticsExpressGetParams();


		YouzanLogisticsExpressGet youzanLogisticsExpressGet = new YouzanLogisticsExpressGet();
		youzanLogisticsExpressGet.setAPIParams(youzanLogisticsExpressGetParams);
		YouzanLogisticsExpressGetResult result = client.invoke(youzanLogisticsExpressGet);
		logger.info("所有的快递公司编码：");
		logger.info(BaseDto.toString(result));

		YouzanLogisticsExpressGetResult.LogisticsExpressOpenApiModel[] allexpress = result.getAllExpress();

		for(YouzanLogisticsExpressGetResult.LogisticsExpressOpenApiModel express : allexpress){



		}


	}



}
