package org.example.cronTrigger;

import org.example.simpleTrigger.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.GregorianCalendar;

public class CronQuartz {
    public static void main(String[] args) throws SchedulerException {
        /*quartz API*/
        /*1.调度器Scheduler（核心组件：触发器和jobDetail都要注册在其中）*/
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        /*2.触发器(约定规则，起始时间、结束时间、执行频率)：给予标识，一个name，一个group*/
        /*此处使用的是Cron触发器(无需指定  时间段（起始时间-终止时间）)*/
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2")
                /*定制一个cron表达式*/
                /*-----------------------年份一般省略不写--cron表达式   秒  时  分  日  月  周-*/
                /*------------------8月11号的每时每分每秒--cron表达式---"* * * 11 8 ?")--*/
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * 11 8 ?"))
                .build();
        /*3.JobDetail(HelloJob中的只是半成品，在此处进行包装)*/
        JobDetail jobDetail=JobBuilder.newJob(HelloJob2.class)
                .withIdentity("job2","group2")
                .build();
        /*4.将JobDetail和触发器增加到调度器中*/
        scheduler.scheduleJob(jobDetail,trigger);
        /*5.启动，调度器开始工作*/
        scheduler.start();
    }
}
