package com.review.demo.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目一：给定一个数组[1,2,3,4,5,6,7,8,9....,15]，要求遍历数组，
 * 遇到可以同时被3和5整除的数字，打印C；
 * 遇到仅能被5整除的数字，打印B；
 * 遇到仅能被3整除的数字，打印A；
 * 其他打印数字本身；
 * <p>
 * 要求四个线程，每一个线程执行一个打印方法。
 */
public class Question3 implements Runnable {

    private static final int[] params = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    private static volatile  int count = 0 ;
    private String type;

    public Question3(String type) {
        this.type = type;
    }

    public String calculate(int param) {
        if (param % 15 == 0) {
            return "C";
        } else if (param % 5 == 0) {
            return "B";
        } else if (param % 3 == 0) {
            return "A";
        } else {
            return "0";
        }
    }

    public static void print(String result) {
        System.out.println(result);
    }


    @Override
    public void run() {
        while(true){
            lock.lock();
            try {
                if (count<params.length&&!type.equals(calculate(params[count]))) {
                    condition.await();
                }
                if(count<params.length){
                    if("0".equals(type)){
                        print(String.valueOf(params[count]));
                    }else {
                        print(type);
                    }
                    count++;
                    condition.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Question3("A")).start();
        new Thread(new Question3("B")).start();
        new Thread(new Question3("C")).start();
        new Thread(new Question3("0")).start();
    }
}
