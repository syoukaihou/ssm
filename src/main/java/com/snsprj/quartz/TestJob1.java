package com.snsprj.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by skh on 2017/7/10.
 */
public class TestJob1 extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {

        System.out.println("正在执行定时任务...");
    }
}
