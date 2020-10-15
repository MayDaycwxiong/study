package com.infinite.life.frame.quartz.scheduler;

import com.infinite.life.frame.quartz.AbstractQuartz;
import com.infinite.life.frame.quartz.QuartzJobDTO;
import com.infinite.life.frame.quartz.job.ConfigJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleTrigger;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Slf4j
public class ConfigSchedulerManager extends AbstractQuartz {

    private static final String CONFIG_RAM_TRIGGER = "CONFIG_RAM_TRIGGER";
    private static final String CONFIG_RAM_JOB = "CONFIG_RAM_JOB";
    private static final int INTERVAL_SECONDS = 5;

    @Override
    protected ScheduleBuilder<SimpleTrigger> getScheduleBuilder() {
        return simpleSchedule().withIntervalInSeconds(INTERVAL_SECONDS).repeatForever();
    }

    @Override
    protected void afterScheduleJobDeal() {
        return;
    }

    @Override
    public void deal() {
        try {
            QuartzJobDTO quartzJobDTO = new QuartzJobDTO(CONFIG_RAM_TRIGGER, CONFIG_RAM_JOB, INTERVAL_SECONDS);
            this.scheduleJob(quartzJobDTO);
        } catch (Exception e) {
            log.error("调度程序出现异常，请检查！！！",e);
        }
    }

    @Override
    protected Class<ConfigJob> getJobClazz() {
        return ConfigJob.class;
    }
}
