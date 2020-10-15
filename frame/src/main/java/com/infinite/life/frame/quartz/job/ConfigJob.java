package com.infinite.life.frame.quartz.job;

import cn.hutool.core.util.StrUtil;
import com.infinite.life.frame.quartz.scheduler.DaySchedulerManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@DisallowConcurrentExecution
public class ConfigJob implements Job {

    private static ConcurrentHashMap<String, String> configMap = new ConcurrentHashMap<>(2);
    private final static String DATETIME_CONFIG_LABLE = "DATETIME_CONFIG";
    private final static String PERIOD_CONFIG_LABLE = "PERIOD_CONFIG";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 读取配置文件，检查配置文件是否变动
        String dateTime = configMap.get(DATETIME_CONFIG_LABLE);
        String period = configMap.get(PERIOD_CONFIG_LABLE);

        String lastDateTime = "";
        if (StrUtil.isEmpty(lastDateTime)) {
            lastDateTime = "02:00:00";
        }

        String lastPeriod = "";
        if (StrUtil.isEmpty(lastPeriod)) {
            lastPeriod = "1";
        }
        if (!lastDateTime.equals(dateTime) || !lastPeriod.equals(period)) {
            configMap.put(DATETIME_CONFIG_LABLE, lastDateTime);
            configMap.put(PERIOD_CONFIG_LABLE, lastPeriod);
            // 调度另一个调度程序
            log.info("调度另一个程序:" + new Date());
            try {
                String[] split = lastDateTime.split(StrUtil.COLON);
                if (split.length == 3) {
                    new DaySchedulerManager(split, Integer.parseInt(lastPeriod)).deal();
                } else {
                    log.info("日期格式错误");
                }
            } catch (Exception e) {
                log.error("调度出现异常"+e);
            }
        } else {
            log.info("值不变:" + new Date());
        }
    }
}
