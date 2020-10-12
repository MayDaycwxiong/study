package com.infinite.life.frame.quartz.scheduler;

import com.infinite.life.frame.quartz.AbstractQuartz;
import com.infinite.life.frame.quartz.QuartzJobDTO;
import com.infinite.life.frame.quartz.job.ConfigJob;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleTrigger;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class ConfigSchedulerManager extends AbstractQuartz {

    private static final String CONFIG_RAM_TRIGGER = "CONFIG_RAM_TRIGGER";
    private static final String CONFIG_RAM_JOB = "CONFIG_RAM_JOB";

    @Override
    protected ScheduleBuilder<SimpleTrigger> getScheduleBuilder() {
        return simpleSchedule().withIntervalInSeconds(5).repeatForever();
    }

    @Override
    protected void doSomething() {
        return;
    }

    @Override
    public void deal() {
        try {
            QuartzJobDTO quartzJobDTO = new QuartzJobDTO();
            quartzJobDTO.setTriggerName(CONFIG_RAM_TRIGGER);
            quartzJobDTO.setJobName(CONFIG_RAM_JOB);
            quartzJobDTO.setPeriod(5);

            this.scheduleJob(quartzJobDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected Class<ConfigJob> getJobClazz() {
        return ConfigJob.class;
    }
}
