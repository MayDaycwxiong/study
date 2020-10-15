package com.infinite.life.frame.quartz;

import java.io.Serializable;
import java.util.Date;

public class QuartzJobDTO implements Serializable {

    /**
     * 触发器名字
     */
    private String triggerName;
    /**
     * 任务名字
     */
    private String jobName;
    /**
     * 运行时间间隔
     */
    private Integer period;
    /**
     * 结束时间
     */
    private Date endTime;

    public QuartzJobDTO(){}

    public QuartzJobDTO(String triggerName, String jobName, Integer period) {
        this.triggerName = triggerName;
        this.jobName = jobName;
        this.period = period;
    }

    public QuartzJobDTO(String triggerName, String jobName, Integer period, Date endTime) {
        this(triggerName, jobName, period);
        this.endTime = endTime;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
