package com.wangqin.shopkeeper.biz1.app.task;

import com.wangqin.shopkeeper.common.utils.LogWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Patrick on 2018/5/17.
 */
@Slf4j
@Component
public class JobBean {
    @Scheduled(cron = "0/1 * * * * ? ")
    public void run(){
        LogWorker.log(log,"Job任务","date:{}",new Date());
    }
}
