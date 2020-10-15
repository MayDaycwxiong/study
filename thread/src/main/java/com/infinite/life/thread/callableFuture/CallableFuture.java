package com.infinite.life.thread.callableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @description     Callable 和 Future 实现线程等待
 * @author cuiwx
 * @versoin 1.0  2019/9/8
 */
@Slf4j
public class CallableFuture {

    public void callableFuture() throws InterruptedException, ExecutionException {
        log.info("start main thread---");

        // 新建一个Callable任务，并将其提交到一个ExecutorService，将返回一个描述任务情况的Future.
        ExecutorService exec= Executors.newFixedThreadPool(2);

        Callable<String> call= () -> {
            log.info("start callable thread");
            Thread.sleep(10*1000);
            log.info("end callable thread");
            return "callable result";
        };

        Future<String> task=exec.submit(call);
        Thread.sleep(5*1000);
        log.info("waitting for the result of callable...");

        String retn=task.get();
        log.info("callable 返回的结果为:"+retn);

        // 关闭线程池
        exec.shutdown();
        log.info("end main thread---");

}

}
