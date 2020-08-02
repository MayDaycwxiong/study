package com.infinite.life.thread.threadlocal.mythreadlocal;

import lombok.var;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cuiwx
 * @version 1.0  2020/8/2
 * @description 实现MyThreadLocal
 */
public class MyThreadLocal<T> {

//    static HashMap<Thread, HashMap<MyThreadLocal<?>, Object>> threadLocalMap = new HashMap<>(4);

//    synchronized static HashMap<MyThreadLocal<?>, Object> getMap() {
//        val thread = Thread.currentThread();
//        if (!threadLocalMap.containsKey(thread)) {
//            threadLocalMap.put(thread, new HashMap<>());
//        }
//        return threadLocalMap.get(thread);
//    }

    protected T initialValue() {
        return null;
    }

//    public T get() {
//        val map = getMap();
//        if (!map.containsKey(this)) {
//            map.put(this, initialValue());
//        }
//        return (T) map.get(this);
//    }
//
//    public void set(T v) {
//        val map = getMap();
//        map.put(this, v);
//    }
// ########################内存优化，解决MyThreadLocal无法回收问题##############################

    public static AtomicInteger atomic = new AtomicInteger();
    private Integer threadLocalHash = atomic.addAndGet(0x61c88647);
    public static HashMap<Thread, HashMap<Integer, Object>> threadLocalMap_better = new HashMap<>(4);

    synchronized static HashMap<Integer, Object> getMap() {
        var thread = Thread.currentThread();
        if (!threadLocalMap_better.containsKey(thread)) {
            threadLocalMap_better.put(thread, new HashMap<Integer, Object>(4));
        }
        return threadLocalMap_better.get(thread);
    }

    public T get() {
        var map = getMap();
        if (!map.containsKey(this.threadLocalHash)) {
            map.put(this.threadLocalHash, initialValue());
        }
        return (T) map.get(this.threadLocalHash);
    }

    public void set(T v) {
        var map = getMap();
        map.put(this.threadLocalHash, v);
    }
}
