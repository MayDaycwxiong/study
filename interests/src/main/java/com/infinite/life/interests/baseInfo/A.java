package com.infinite.life.interests.baseInfo;

public class A extends B{
    /**
     * 静态变量
     */
    static int i=1;
    // 静态语句块
    static{
        System.out.println("Class A1:static blocks"+i);
    }
    /**
     * 非静态变量
     */
    int j=1;
    // 静态语句块
    static{
        i++;
        System.out.println("Class A2:static blocks"+i);
    }
    /**
     * @description     构造函数
     * @autohr cuiwx  2019/9/15
     * @return
     */
    public A(){
        super();
        i++;
        j++;
        System.out.println("constructor A:"+"i="+i+",j="+j);
    }
    // 非静态语句块
    {
        i++;
        j++;
        System.out.println("Class A:common blocks"+"i="+i+",j="+j);
    }
    /**
     * @description     非静态方法
     * @autohr cuiwx  2019/9/15
     * @return
     */
    public void aDisplay(){
        i++;
        System.out.println("Class A:static void aDisplay():"+"i="+i+",j="+j);
    }
    /**
     * @description     静态方法
     * @autohr cuiwx  2019/9/15
     * @return
     */
    public static void aTest(){
        i++;
        System.out.println("Class A:static void aTest():"+"i="+i);
        return;
    }

}
