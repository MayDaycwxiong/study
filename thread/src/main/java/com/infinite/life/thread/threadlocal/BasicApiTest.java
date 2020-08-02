package com.infinite.life.thread.threadlocal;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * @author cuiwx
 * @version 1.0  2020/8/1
 * @description 基本API测试
 */
@Slf4j
public class BasicApiTest {
    /**
     * #########1.直接get，值为null[初始值为null]#########
     */
    public static ThreadLocal<Long> threadLocal1 = new ThreadLocal<>();
    /**
     * 2.get的时候触发 initialValue 函数，initialValue 懒加载模式，第一次get的时候触发，多次get只执行一次
     */
    public static ThreadLocal<Long> threadLocal2 = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            log.warn("2# Initial value run...");
            return 100L;
        }
    };

    public static ThreadLocal<Long> threadLocal3 = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            log.warn("3# Initial value run...");
            return Thread.currentThread().getId();
        }
    };
    /**
     * 4.如果先调用threadLocal4.set(100L),则initialValue 不会执行,remove 移除当前设置的值，再次调用get会触发initialValue
     */
    public static ThreadLocal<Long> threadLocal4 = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            log.warn("4# Initial value run...");
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch("ThreadLocal片段测试");
        // ##################
        stopWatch.start("1.直接get，值为null[初始值为null]");
        log.info("1# " + StrUtil.EMPTY + threadLocal1.get());
        stopWatch.stop();

        stopWatch.start("2.get的时候触发 initialValue 函数，initialValue 懒加载模式，第一次get的时候触发，多次get只执行一次");
        log.info("2# " + StrUtil.EMPTY + threadLocal2.get());
        log.info("2# " + StrUtil.EMPTY + threadLocal2.get());
        threadLocal2.set(200L);
        log.info("2# " + StrUtil.EMPTY + threadLocal2.get());
        stopWatch.stop();

        stopWatch.start("3.各线程保留ThreadLocal是线程本地的变量，每个线程本地会拥有一个，看起来是同一个变量，但是不同线程访问的时候访问的是不同的实例");
        log.info("3# " + StrUtil.EMPTY + threadLocal3.get());
        new Thread() {
            @Override
            public void run() {
                log.info("3# " + StrUtil.EMPTY + threadLocal3.get());
            }
        }.start();
        log.info("3# " + StrUtil.EMPTY + threadLocal3.get());
        threadLocal3.set(1000L);
        log.info("3# " + StrUtil.EMPTY + threadLocal3.get());
        stopWatch.stop();

        stopWatch.start("4.如果先调用threadLocal4.set(100L),则initialValue 不会执行,remove 移除当前设置的值，再次调用get会触发initialValue");
        threadLocal4.set(1024L);
        log.info("4# " + StrUtil.EMPTY + threadLocal4.get());
        threadLocal4.remove();
        log.info("4# " + StrUtil.EMPTY + threadLocal4.get());

        log.info("#######################分析#######################");
        log.info(stopWatch.prettyPrint());
    }
}
