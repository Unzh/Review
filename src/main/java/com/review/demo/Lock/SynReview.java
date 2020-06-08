package com.review.demo.Lock;

import java.util.concurrent.CountDownLatch;

/**
 * Title : SynReview.java
 * Package : com.review.demo.Lock
 * Description : <p>
 * synchronized 实现加锁
 * </p>
 * Create on : 2020/6/2
 *
 */
public class SynReview implements Runnable {

    private CountDownLatch countDownLatch;

    public SynReview(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void doing(String name) {
        synchronized (SynReview.class) {
            System.out.println("initial:" + name);
            System.out.println("线程名字：" + name);
        }
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            doing(Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
