package com.snsprj.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by skh on 2017/7/10.
 */
@Component
public class TestTask {

    // 秒 分 小时	 日期 月份 星期(日期和星期互斥，必须有一个设置为"?")
    //@Scheduled(cron = "0 0/1 * * * ?")
    public void doTask(){
        System.out.println("基于注解的spring定时任务正在执行...");
    }
}
