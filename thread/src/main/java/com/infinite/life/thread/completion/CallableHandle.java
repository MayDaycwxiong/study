package com.infinite.life.thread.completion;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CallableHandle implements Callable<Integer> {

    private int sleepSeconds;
    private int returnValue;

    public CallableHandle(int sleepSeconds,int returnValue){
        this.returnValue=returnValue;
        this.sleepSeconds=sleepSeconds;
    }

    @Override
    public Integer call() throws Exception {
        log.info("begin to execute CallableHandle "+returnValue);

        TimeUnit.SECONDS.sleep(sleepSeconds);
        return returnValue;
    }
}
