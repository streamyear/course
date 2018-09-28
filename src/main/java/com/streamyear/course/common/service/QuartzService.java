package com.streamyear.course.common.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

@Component
public class QuartzService {
    private String JOB_NAME = "JOB_NAME_COURSE";
    private String JOB_GROUP_NAME = "JOB_GROUP_NAME_COURSE";
    private String TRIGGER_NAME = "TRIGGER_NAME_COURSE";
    private String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME_COURSE";

    /**
     * 注入调度器
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     * @param jobClass:任务
     * @param time:时间设置，CronExpression表达式
     */
    public void addJob(@SuppressWarnings("rawtypes") Class jobClass, String time) {
        addJob(jobClass,time,JOB_NAME,JOB_GROUP_NAME,TRIGGER_NAME,TRIGGER_GROUP_NAME);
    }

    /**
     * @Description: 添加一个定时任务
     * @param jobClass:任务
     * @param time:时间设置，CronExpression表达式
     * @param jobName:任务名
     * @param jobGroupName:任务组名
     * @param triggerName:触发器名
     * @param triggerGroupName:触发器组名
     */
    public void addJob(@SuppressWarnings("rawtypes") Class jobClass, String time,
                              String jobName, String jobGroupName, String triggerName, String triggerGroupName) {

        JobDetail job = newJob(jobClass).withIdentity(jobName, jobGroupName).build();
        CronTrigger trigger = newTrigger().withIdentity(triggerName, triggerGroupName)
                .withSchedule(cronSchedule(time)).build();
        try {
            // 返回为 null 添加失败
            Date ft = scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 定义一个任务之后进行触发设定(使用默认的任务组名，触发器名，触发器组名)
     * @param time
     */
    @SuppressWarnings("rawtypes")
    public void addJObLaterUse(@SuppressWarnings("rawtypes") Class jobClass, String time) {
        addJObLaterUse(jobClass,time,JOB_NAME,JOB_GROUP_NAME);
    }

    /**
     * @Description: 定义一个任务之后进行触发设定
     * @param time
     * @param jobName:任务名
     * @param jobGroupName:任务组名
     */
    @SuppressWarnings("rawtypes")
    public void addJObLaterUse(@SuppressWarnings("rawtypes") Class jobClass, String time,
                                      String jobName,String jobGroupName) {

        JobDetail job = newJob(jobClass).withIdentity(jobName, jobGroupName).storeDurably().build();

        try {
            scheduler.addJob(job, false);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 对已存储的任务进行scheduling(使用默认的任务组名，触发器名，触发器组名)
     * @param time
     */
    @SuppressWarnings("rawtypes")
    public void schedulingStoredJOb(@SuppressWarnings("rawtypes") Class jobClass, String time) {
        schedulingStoredJOb(jobClass,time,JOB_NAME,JOB_GROUP_NAME,TRIGGER_NAME,TRIGGER_GROUP_NAME);
    }

    /**
     * @Description: 对已存储的任务进行scheduling
     * @param time
     * @param jobName:任务名
     * @param jobGroupName:任务组名
     */
    @SuppressWarnings("rawtypes")
    public void schedulingStoredJOb(@SuppressWarnings("rawtypes") Class jobClass, String time,
                                           String jobName,String jobGroupName,String triggerName, String triggerGroupName) {
        Trigger trigger = newTrigger().withIdentity(triggerName, triggerGroupName).startNow()
                .forJob(jobKey(jobName,jobGroupName))
                .build();
        try {
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     * @param time
     */
    @SuppressWarnings("rawtypes")
    public void modifyJobTime(String time) {
        modifyJobTime(TRIGGER_NAME, TRIGGER_GROUP_NAME, time);
    }

    /**
     * @Description: 修改一个任务的触发时间
     * @param triggerName
     * @param triggerGroupName
     * @param time
     */
    public void modifyJobTime(String triggerName, String triggerGroupName, String time) {
        Trigger trigger = newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(cronSchedule(time)).startNow().build();

        try {
            scheduler.rescheduleJob(triggerKey(triggerName, triggerGroupName), trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 修改一个任务(使用默认的任务组名，任务名)
     */
    @SuppressWarnings("rawtypes")
    public void modifyJob(@SuppressWarnings("rawtypes") Class jobClass) {
        modifyJob(jobClass,JOB_NAME,JOB_GROUP_NAME);
    }

    /**
     * @Description: 修改一个任务
     * @param jobName:任务名
     * @param jobGroupName:任务组名
     */
    public void modifyJob(@SuppressWarnings("rawtypes") Class jobClass, String jobName,String jobGroupName) {
        JobDetail job1 = newJob(jobClass).withIdentity(jobName,jobGroupName).build();
        try {
            scheduler.addJob(job1, true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 删除一个任务的的trigger
     * @param triggerName
     * @param triggerGroupName
     */
    public void unschedulingJob(String triggerName, String triggerGroupName) {
        try {
            scheduler.unscheduleJob(triggerKey(triggerName,triggerGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 移除一个任务，以及任务的所有trigger
     * @param jobName
     */
    public void removeJob(String jobName,String jobGroupName) {
        try {
            scheduler.deleteJob(jobKey(jobName,jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                //未传参或false：不等待执行完成便结束；true：等待任务执行完才结束
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
