package com.infinite.life.interests.baseInfo;
/**
 * @description     验证类加载的顺序
 * @author cuiwx
 * @versoin 1.0  2019/9/15
 */
public class ClassLoadingOrder {

    static int num=4;

    {
        num+=3;
        System.out.println("b");
    }

    int a=5;

    {
        System.out.println("c");
    }

    ClassLoadingOrder(){
        System.out.println("d");
    }

    static {
        System.out.println("a");
    }

    static void run(){
        System.out.println("e");
    }
}
