package com.wangqin.globalshop.biz1.app.task;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.wangqin.globalshop.common.utils.LogWorker;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Patrick on 2018/5/17.
 */
@Slf4j
@Component
public class JobBean {
//    @Scheduled(cron = "0/1 * * * * ? ")
    public void run(){
        LogWorker.log(log,"Job任务","date:{}",new Date());
    }
}
