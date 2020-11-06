package com.review.demo.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * 简易实现多线程
 *
 * @Author ZH
 * @version 1.0.0
 * @Date 2020/11/6
 */
public class SimpleMultithreading {

    private static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            String a  = i+" ";
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(a+Thread.currentThread().getName()+ " is running");
                }
            });
        }
        service.shutdown();
        System.out.println("~~~~~~");
    }

}
