package com.infinite.life.interests.baseInfo;

public class B {
    /**
     * 静态变量
     */
    static int i=1;
    // 静态语句块
    static{
        System.out.println("Class B1:static blocks"+i);
    }

    /**
     * 非静态变量
     */
    int j=1;
    // 静态语句块
    static{
        i++;
        System.out.println("Class B2:static blocks"+i);
    }
    /**
     * 构造函数
     */
    public B(){
        i++;
        j++;
        System.out.println("constructor B:"+"i="+i+",j="+j);
    }
    // 非静态语句块
    {
        i++;
        j++;
        System.out.println("Class B:common blocks"+" i="+i+",j="+j);
    }

    /**
     * 非静态方法
     */
    public void bDisplay(){
        i++;
        System.out.println("Class B:static void bDisplay():"+"i="+i+",j="+j);
        return;
    }
    /**
     * @description     静态方法
     * @autohr cuiwx  2019/9/15
     * @return
     */
    public static void bTest(){
        i++;
        System.out.println("Class B:static void bTest():"+"i="+i);
        return;
    }
}
