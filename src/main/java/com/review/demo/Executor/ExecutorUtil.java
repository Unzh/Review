package com.review.demo.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Java通过Executors提供四种线程池
 */
public class ExecutorUtil {

    //默认初始化线程数
    private int initialValue = 5;

    //最高并发数
    private int MaximumConcurrent = 10;

    /**
     * newCacheThreadPool 线程池
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     */
    public void testCacheThreadPool() {
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < MaximumConcurrent; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cacheThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

    /**
     * newFixedThreadPool 线程池
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     * 定长线程池的大小最好根据系统资源进行设置
     */
    public void TestFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(initialValue);
        for (int i = 0; i < MaximumConcurrent; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * newScheduledThreadPool 线程池
     * 创建一个定长线程池，支持定时及周期性任务执行
     */
    public void TestScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(initialValue);
        /**
         * 延迟执行代码
         * 表示延迟3秒执行。
         */
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);

        /**
         * 定期执行代码
         * 表示延迟1秒后每3秒执行一次。
         */
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    /**
     * newSingleThreadExecutor 线程池
     */
    public void TestSingleThreadPool() {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        /**
         * 结果依次输出，相当于顺序执行各个任务。
         */
        for (int i = 0; i < MaximumConcurrent; i++) {
            final int index = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static String Test() {
        int i = 1;
        for (; ; ) {
            if (i < 5) {
                System.out.println(i);
                i++;
            }
            if (i > 5) {
                return "6";
            }
            if(i==5){
                i++;
                continue;
            }
        }
    }

    public static void main(String[] args) {
        String result = Test();
        System.out.println(result);
    }

}
