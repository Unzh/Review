package com.review.demo.Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title : LockReview.java
 * Package : com.review.demo.Lock
 * Description : <p>
 * Lock 实现加锁 ReentrantLock 来说明
 * </p>
 * Create on : 2020/6/2
 */
public class LockReview implements Runnable {

    private Lock lock = new ReentrantLock();

    private CountDownLatch countDownLatch;

    public LockReview(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void doing(String name) {
        System.out.println("initial:" + name);
        lock.lock();
        try {
            System.out.println("线程名字：" + name);
        } finally {
            lock.unlock();
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
