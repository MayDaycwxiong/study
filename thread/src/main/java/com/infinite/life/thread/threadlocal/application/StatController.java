package com.infinite.life.thread.threadlocal.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class StatController {

    static HashSet<Val<Integer>> hashSet=new HashSet<>();


    static ThreadLocal<Val<Integer>> count = new ThreadLocal<Val<Integer>>(){
        @Override
        protected Val<Integer> initialValue() {
            Val<Integer> val=new Val<>();
            val.setT(0);
            this.hashSetAdd(val);
            return val;
        }

        private synchronized void hashSetAdd(Val<Integer> val) {
            hashSet.add(val);
        }
    };


    @RequestMapping("/getCount")
    public Integer getCount() {
        return hashSet.stream().map(Val::getT).reduce((a, x) -> a + x).get();
    }

    @RequestMapping("addCount")
    public Integer addCount() throws InterruptedException {
        this.add();
        return 1;
    }

    private void add() throws InterruptedException {
        log.info("add invoke ----");
        TimeUnit.MILLISECONDS.sleep(50);
        Val<Integer> val=count.get();
        val.setT(val.getT()+1);
    }
}
