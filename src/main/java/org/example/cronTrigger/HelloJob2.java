package org.example.cronTrigger;

import org.quartz.*;

import java.util.Date;

public class HelloJob2 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        /*可以调取JobDetail中的标识（name和group，在key中）*/
        JobDetail jobDetail=context.getJobDetail();
        JobKey key = jobDetail.getKey();
        System.out.println(key.getGroup());
        System.out.println(key.getName());
        /*任务中想实现的逻辑*/
        System.out.println("Hello CronJob "+new Date());
    }
}
