package com.review.demo.Executor;

/***
 * 确认子线程中静态代码块和构造方法是由new 线程执行
 *
 * @Author ZH
 * @version 1.0.0
 * @Date 2020/11/24
 */
public class SubThread extends Thread{

    public SubThread() {
        System.out.println(currentThread().getName());
        System.out.println("SubThread构造方法.....");
    }

    static {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(currentThread().getName());
        System.out.println("SubThread静态代码块.....");
    }


    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(currentThread().getName());
        System.out.println("子线程... is running");
    }
}
