package com.streamyear.course.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date fireTime = jobExecutionContext.getFireTime();
        System.out.println("执行定时任务的时间为: " + fireTime);
    }
}
