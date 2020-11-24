package com.review.demo.test;

import com.review.demo.Executor.SubThread;

/***
 * 测试子线程中静态代码块和构造方法是否由new 线程执行
 *
 * @Author ZH
 * @version 1.0.0
 * @Date 2020/11/24
 */
public class SimpleThreadTest {


    public static void main(String[] args) {

        Thread thread1 = new SubThread();
        thread1.start();
        System.out.println("~~~~~~~~~~~~~~~");


    }
}
