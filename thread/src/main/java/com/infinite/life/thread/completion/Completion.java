package com.infinite.life.thread.completion;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author cuiwx
 * @description 使用CompletionService
 * @versoin 1.0  2019/9/8
 */
@Slf4j
public class Completion {

    public void handle() {
        long start = System.nanoTime();
        int taskSize = 5;
        ExecutorService exec = Executors.newFixedThreadPool(taskSize);

        // 构建完成服务
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(exec);

        for (int i = 1; i <= taskSize; i++) {
            // 睡眠时间
            int sleep = taskSize - 1;
            // 返回结果
            int value = i;
            // 向线程池提交任务
            completionService.submit(new CallableHandle(sleep, value));
        }
        for (int i = 1; i <= taskSize; i++) {
            try {
                int result = completionService.take().get();
                log.info("result=" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 所有任务都完成，关闭线程池
        log.info("all thread finished");
        exec.shutdown();
        log.info("all program execute time is:" + (System.nanoTime() - start));
    }
}
