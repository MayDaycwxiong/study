package com.infinite.life.thread.latch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HarnessMain {
    public void handle() {
        Runnable task = () -> log.info("执行任务，我是线程:" + Thread.currentThread().getName());
        int count = 10;
        Harness harness = new Harness(count, task);
        long time = harness.timeTask();
        log.info("闭锁==》测试结果=》执行" + count + "个线程，一共用时:" + time);
    }
}
