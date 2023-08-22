package org.example.simpleTrigger;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.GregorianCalendar;

public class HelloQuartz {
    public static void main(String[] args) throws SchedulerException {
        /*quartz API*/
        /*1.调度器Scheduler（核心组件：触发器和jobDetail都要注册在其中）*/
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        /*2.触发器(约定规则，起始时间、结束时间、执行频率)：给予标识，一个name，一个group*/
        /*此处使用的是简单的触发器*/
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow()                                                                   /*withRepeatCount（）规定执行次数，实际会多一次，因为开始就执行一次不算在次数内*/
                /*更详细的执行规则------------------------------------1秒执行一次，repeatForever()一直执行下去，直到endtime*/
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(10))
                .endAt(new GregorianCalendar(2023, 8, 11, 22, 15, 30).getTime())
                .build();
        /*3.JobDetail(HelloJob中的只是半成品，在此处进行包装)*/
        JobDetail jobDetail=JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1","group1")/*此处的group1与trigger的group1完美隔离，不会冲突*/
                .build();
        /*4.将JobDetail和触发器增加到调度器中*/
        scheduler.scheduleJob(jobDetail,trigger);
        /*5.启动，调度器开始工作*/
        scheduler.start();
    }
}
