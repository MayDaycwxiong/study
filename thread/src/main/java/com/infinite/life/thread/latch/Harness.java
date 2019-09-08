package com.infinite.life.thread.latch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Harness {

    private int nThreads;
    private Runnable task;

    public Harness(int count, Runnable task) {
        this.nThreads = count;
        this.task = task;
    }

    public long timeTask() {
        // 起始门
        final CountDownLatch startGate = new CountDownLatch(1);
        // 结束门
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread thread = new Thread(() -> {
                // 每个线程在启动门上等待，确保所有线程都就绪后才开始
                try {
                    startGate.await();
                    log.info(Thread.currentThread().getName() + "的闭锁测试开始时间:" + System.currentTimeMillis());
                    try {
                        task.run();
                    } finally {
                        // 每个线程结束后，调用countDown递减计数器，表示一个事件发生
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        long start = System.nanoTime();
        // 启动门发生
        startGate.countDown();
        try {
            // 等待结束门的线程都结束
            endGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        return end - start;
    }
}
