package com.infinite.life.thread.completion;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description     传统处理方式
 * @author cuiwx
 * @versoin 1.0  2019/9/8
 */
@Slf4j
public class TraditionalStrategy {

    public void handle(){
        long start=System.nanoTime();

        int taskSize=5;
        ExecutorService exec= Executors.newFixedThreadPool(taskSize);
        List<Future<Integer>> futureList=new ArrayList<>();

        // 向线程池提交任务
        for(int i=1;i<=taskSize;i++){
            int sleep=taskSize-1;
            int value=i;
            Future<Integer> future=exec.submit(new CallableHandle(sleep,value));
            // 保留每个任务的Future
            futureList.add(future);
        }

        // 轮询，获取完成任务的返回结果
        while(taskSize>0){
            for(Future<Integer> future:futureList){
                Integer result=null;
                try {
                    result=future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                // 任务已经完成
                if(result!=null){
                    log.info("result = "+ result);
                    // 从future列表移除已完成的任务
                    futureList.remove(future);
                    taskSize--;
                    break;
                }
            }
        }
        // 所有任务都完成，关闭线程池
        log.info("all thread finished");
        exec.shutdown();
        log.info("all program execute time is:"+(System.nanoTime()-start));
    }
}
