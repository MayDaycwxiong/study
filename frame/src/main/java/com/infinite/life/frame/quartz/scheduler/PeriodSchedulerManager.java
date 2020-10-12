package com.infinite.life.frame.quartz.scheduler;

import com.infinite.life.frame.quartz.AbstractQuartz;
import com.infinite.life.frame.quartz.QuartzJobDTO;
import com.infinite.life.frame.quartz.job.PeriodJob;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleTrigger;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class PeriodSchedulerManager extends AbstractQuartz {

    private static final String PERIOD_RAM_TRIGGER="PERIOD_RAM_TRIGGER";
    private static final String PERIOD_RAM_JOB="PERIOD_RAM_JOB";

    private int periodMinutes;

    public PeriodSchedulerManager(int period){
        this.periodMinutes=period;
    }

    @Override
    public void deal() {
        try {
            QuartzJobDTO quartzJobDTO=new QuartzJobDTO();
            quartzJobDTO.setTriggerName(PERIOD_RAM_TRIGGER);
            quartzJobDTO.setJobName(PERIOD_RAM_JOB);
            quartzJobDTO.setPeriod(periodMinutes);
            quartzJobDTO.setEndTime(dateOf(8, 30, 59));
            this.scheduleJob(quartzJobDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected ScheduleBuilder<SimpleTrigger> getScheduleBuilder() {
        return simpleSchedule().withIntervalInMinutes(periodMinutes).repeatForever();
    }

    @Override
    protected void doSomething() {
        return;
    }

    @Override
    protected Class<PeriodJob> getJobClazz() {
        return PeriodJob.class;
    }
}
