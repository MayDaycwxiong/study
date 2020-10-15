package com.infinite.life.frame.quartz.job;

import com.infinite.life.frame.quartz.scheduler.PeriodSchedulerManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@Slf4j
@DisallowConcurrentExecution
public class DayJob implements Job {

    private final static String PERIOD = "PERIOD";


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        int period = jobDataMap.getInt(PERIOD);
        log.info(String.format("对账文件下载时间间隔为[%s]", period));
        new PeriodSchedulerManager(period).deal();
    }
}
