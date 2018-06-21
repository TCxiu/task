package com.xiu.quartz.demo.service;

import com.xiu.quartz.demo.model.JobModel;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/20 14:03
 * @Description 类描述:
 */

@Service
public class TaskService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public Date addTask(JobModel jobModel){

        Class<? extends Job> jobClass = null;

        try {
            jobClass = (Class<? extends Job>) Class.forName("com.xiu.quartz.demo.job."+jobModel.getJobClassName());
        } catch (ClassNotFoundException e) {

        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobModel.getJobName(), jobModel.getJobGroup()).build();

        //触发器构建
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobModel.getTriggerName(), jobModel.getTriggerGroup()).withSchedule(CronScheduleBuilder.cronSchedule(jobModel.getCronExpression())).build();
        try {
            return scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            return null;
        }
    }

    public Boolean delTask(Map<String,String> map){

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        boolean flag = false;
        try {
            flag = scheduler.deleteJob(JobKey.jobKey(map.get("jobName"), map.get("jobGroup")));
        } catch (SchedulerException e) {

        }
        return flag;
    }

    public Date updateTask(JobModel jobModel){

        Scheduler scheduler = schedulerFactoryBean.getScheduler();


        TriggerKey triggerKey = TriggerKey.triggerKey(jobModel.getTriggerName(), jobModel.getTriggerGroup());

        CronTrigger trigger = null;
        try {
            trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {

        }

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobModel.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        try {
            return scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {

        }
        return null;
    }
}
