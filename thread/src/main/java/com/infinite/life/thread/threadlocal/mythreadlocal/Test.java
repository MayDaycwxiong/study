package com.infinite.life.thread.threadlocal.mythreadlocal;

public class Test {

    public static MyThreadLocal<Long> value=new MyThreadLocal<Long>(){
        @Override
        protected Long initialValue(){
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(value.get());
            }).start();
        }
    }
}
