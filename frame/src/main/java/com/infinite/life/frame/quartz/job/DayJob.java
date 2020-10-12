package com.infinite.life.frame.quartz.job;

import com.infinite.life.frame.quartz.scheduler.PeriodSchedulerManager;
import org.quartz.*;

@DisallowConcurrentExecution
public class DayJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("触发job");
        JobDataMap jobDataMap=context.getMergedJobDataMap();
        int period=jobDataMap.getInt("PERIOD");
        System.out.println("接收到值："+period);
        new PeriodSchedulerManager(period).deal();
    }
}
