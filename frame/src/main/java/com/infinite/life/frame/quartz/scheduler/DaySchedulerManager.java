package com.infinite.life.frame.quartz.scheduler;

import com.infinite.life.frame.quartz.AbstractQuartz;
import com.infinite.life.frame.quartz.QuartzJobDTO;
import com.infinite.life.frame.quartz.job.DayJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.ScheduleBuilder;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
@Slf4j
public class DaySchedulerManager extends AbstractQuartz {

    private static final String DAY_RAM_TRIGGER = "DAY_RAM_TRIGGER";
    private static final String DAY_RAM_JOB = "DAY_RAM_JOB";

    private String cronExpression;
    private Integer period;


    public DaySchedulerManager(String[] dateTime, Integer period) {
        this.cronExpression = "0 0/5 9-2 * * ? *";
        this.period = period;
    }

    @Override
    public void deal() {
        try {
            QuartzJobDTO quartzJobDTO = new QuartzJobDTO(DAY_RAM_TRIGGER, DAY_RAM_JOB, period);
            this.scheduleJob(quartzJobDTO);
        } catch (Exception e) {
            log.error("调度程序出现异常，请检查！！！", e);
        }
    }

    @Override
    protected ScheduleBuilder<CronTrigger> getScheduleBuilder() {
        return cronSchedule(cronExpression);
    }

    @Override
    protected void afterScheduleJobDeal() {
        try {
            log.info("日期改变调度另一个程序：" + new Date());
            new PeriodSchedulerManager(period).deal();
        } catch (Exception e) {
            log.error("调度程序出现异常，请检查！！！", e);
        }
    }

    @Override
    protected Class<DayJob> getJobClazz() {
        return DayJob.class;
    }
}
