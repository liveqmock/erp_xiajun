package com.wangqin.globalshop.schedule.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.common.enums.ItemIsSale;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.schedule.service.ScheduleItemService;

//@Component
public class TestJob {

	@Autowired
	private ScheduleItemService scheduleItemService;
	 
    @Scheduled(cron = "0 * * * * ? ")
    public void testJob(){
        System.out.println("========Test Job==========");
    }

}
