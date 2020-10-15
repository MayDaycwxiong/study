package com.infinite.life.frame.quartz.scheduler;

import com.infinite.life.frame.quartz.AbstractQuartz;
import com.infinite.life.frame.quartz.QuartzJobDTO;
import com.infinite.life.frame.quartz.job.PeriodJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleTrigger;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
@Slf4j
public class PeriodSchedulerManager extends AbstractQuartz {

    private static final String PERIOD_RAM_TRIGGER = "PERIOD_RAM_TRIGGER";
    private static final String PERIOD_RAM_JOB = "PERIOD_RAM_JOB";

    private int periodMinutes;

    public PeriodSchedulerManager(int period) {
        this.periodMinutes = period;
    }

    @Override
    public void deal() {
        try {
            QuartzJobDTO quartzJobDTO = new QuartzJobDTO(PERIOD_RAM_TRIGGER, PERIOD_RAM_JOB, periodMinutes,null);
            this.scheduleJob(quartzJobDTO);
        } catch (Exception e) {
            log.error("调度程序出现异常，请检查！！！", e);
        }
    }

    @Override
    protected ScheduleBuilder<SimpleTrigger> getScheduleBuilder() {
        return simpleSchedule().withIntervalInMinutes(periodMinutes);
    }

    @Override
    protected void afterScheduleJobDeal() {
        return;
    }

    @Override
    protected Class<PeriodJob> getJobClazz() {
        return PeriodJob.class;
    }
}
