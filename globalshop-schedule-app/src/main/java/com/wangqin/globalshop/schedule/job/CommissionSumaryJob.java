package com.wangqin.globalshop.schedule.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Create by 777 on 2018/7/31
 */

@Component
public class CommissionSumaryJob {


	/***
	 * 查看结算申请表mall_commision_apply
	 * 1、先查：已签收，且未生成佣金的记录
	 * 2、计算总佣金
	 * 3、生成 佣金记录，默认状态待结算
	 */
	@Scheduled(cron = "0 0/5 * * * ? ")
	public void createCommissionSumary(){



	}


	/**
	 *  校验状态，检验是否已签收15天，是则可结算
	 */
	@Scheduled(cron = "30 0/5 * * * ? ")
	public void checkStatusCommissionSumary(){

	}


}
