package com.review.demo.Lock;

import java.util.concurrent.CountDownLatch;

/**
 * Title : Test.java
 * Package : com.review.demo.Lock
 * Description : <p>
 * 测试加锁代码的实现
 * </p>
 * Create on : 2020/6/2
 *
 */
public class Test {

    public void doTest() throws InterruptedException {
        final int N = 5; // 线程数
        CountDownLatch countDownLatch = new CountDownLatch(N);
        for (int i = 0; i < N; i++) {
//            new Thread(new LockReview(countDownLatch)).start();
            new Thread(new SynReview(countDownLatch)).start();
            countDownLatch.countDown();
        }
    }


    public static void main(String[] args) throws Exception {
        Test t1 = new Test();
        t1.doTest();
    }
}
