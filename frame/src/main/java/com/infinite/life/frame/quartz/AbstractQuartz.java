package com.infinite.life.frame.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.TriggerKey.triggerKey;

public abstract class AbstractQuartz<T extends ScheduleBuilder<Trigger>> {
    private final static String RAM_TRIGGER_GROUP = "RAM_TRIGGER_GROUP";

    private final static String RAM_JOB_GROUP = "RAM_JOB_GROUP";

    private final static String PERIOD = "PERIOD";

    private static Scheduler configScheduler = null;

    protected abstract T getScheduleBuilder();

    protected abstract void afterScheduleJobDeal();

    public abstract void deal();

    protected abstract <C> Class<C> getJobClazz();


    private static Scheduler getConfigScheduler() throws Exception {
        if (configScheduler == null) {
            SchedulerFactory factory = new StdSchedulerFactory();
            configScheduler = factory.getScheduler();
        }
        return configScheduler;
    }

    public void scheduleJob(QuartzJobDTO quartzJob) throws Exception {
        Scheduler scheduler = getConfigScheduler();
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
        Trigger dayTrigger = scheduler.getTrigger(triggerKey(quartzJob.getTriggerName(), RAM_TRIGGER_GROUP));
        if (dayTrigger == null) {
            dayTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(quartzJob.getTriggerName(), RAM_TRIGGER_GROUP)
                    .startNow()
                    .withSchedule(this.getScheduleBuilder())
                    .endAt(quartzJob.getEndTime())
                    .build();
            scheduler.deleteJob(JobKey.jobKey(quartzJob.getJobName(), RAM_JOB_GROUP));
            // 第一次
            JobDetail dayJob = getMyJob(quartzJob.getJobName(), quartzJob.getPeriod());
            scheduler.scheduleJob(dayJob,dayTrigger);
            this.afterScheduleJobDeal();
        } else {
            JobDetail dayJob = getMyJob(quartzJob.getJobName(), quartzJob.getPeriod());
            scheduler.addJob(dayJob, true);
            TriggerBuilder triggerBuilder = dayTrigger.getTriggerBuilder();
            Trigger newTrigger = triggerBuilder
                    .startNow()
                    .withSchedule(this.getScheduleBuilder())
                    .endAt(quartzJob.getEndTime())
                    .build();
            scheduler.rescheduleJob(dayTrigger.getKey(), newTrigger);
            this.afterScheduleJobDeal();
        }
    }

    protected JobDetail getMyJob(String jobName, Integer period) {
        return JobBuilder.newJob(this.getJobClazz())
                .withIdentity(jobName, RAM_JOB_GROUP)
                .usingJobData(PERIOD, period)
                .storeDurably(true)
                .build();
    }


}
