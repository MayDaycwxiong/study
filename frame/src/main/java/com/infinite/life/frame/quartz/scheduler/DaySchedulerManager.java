package com.infinite.life.frame.quartz.scheduler;

import com.infinite.life.frame.quartz.AbstractQuartz;
import com.infinite.life.frame.quartz.QuartzJobDTO;
import com.infinite.life.frame.quartz.job.DayJob;
import org.quartz.CronTrigger;
import org.quartz.ScheduleBuilder;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class DaySchedulerManager extends AbstractQuartz {

    private static final String DAY_RAM_TRIGGER = "DAY_RAM_TRIGGER";
    private static final String DAY_RAM_JOB = "DAY_RAM_JOB";

    private String cronExpression = null;
    private Integer period = null;


    public DaySchedulerManager(String[] dateTime, Integer period) {
        this.cronExpression = String.format("%s %s %s * * ? *", dateTime[2], dateTime[1], dateTime[0]);
        this.period = period;
    }

    @Override
    public void deal() {
        try {
            QuartzJobDTO quartzJobDTO = new QuartzJobDTO();
            quartzJobDTO.setTriggerName(DAY_RAM_TRIGGER);
            quartzJobDTO.setJobName(DAY_RAM_JOB);
            quartzJobDTO.setPeriod(period);

            this.scheduleJob(quartzJobDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected ScheduleBuilder<CronTrigger> getScheduleBuilder() {
        return cronSchedule(cronExpression);
    }

    @Override
    protected void doSomething() {
        try {
            System.out.println("日期改变调度另一个程序：" + new Date());
            new PeriodSchedulerManager(period).deal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected Class<DayJob> getJobClazz() {
        return DayJob.class;
    }
}
