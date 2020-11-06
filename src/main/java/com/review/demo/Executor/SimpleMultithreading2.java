package com.review.demo.Executor;

/***
 * 简易实现多线程
 *
 * @Author ZH
 * @version 1.0.0
 * @Date 2020/11/6
 */
public class SimpleMultithreading2 {

    static class MyThread1 extends Thread{
        @Override
        public void run() {
            System.out.println("MyThread1 "+Thread.currentThread().getName()+" is running");
        }
    }
    static class MyThread2 extends Thread{
        @Override
        public void run() {
            System.out.println("MyThread2 "+Thread.currentThread().getName()+" is running");
        }
    }

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.start();
        myThread2.start();
    }


}
