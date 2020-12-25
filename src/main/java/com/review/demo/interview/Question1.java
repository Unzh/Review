package com.review.demo.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 题目一：给定一个数组[1,2,3,4,5,6,7,8,9....,15]，要求遍历数组，
 * 遇到可以同时被3和5整除的数字，打印C；
 * 遇到仅能被5整除的数字，打印B；
 * 遇到仅能被3整除的数字，打印A；
 * 其他打印数字本身；
 * <p>
 * 要求四个线程，每一个线程执行一个打印方法。
 */
public class Question1 {

    private static final int[] params = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    private static ExecutorService executorService = Executors.newFixedThreadPool(4);


    public String calculate(int param) {
        if (param % 3 == 0 && param % 5 == 0) {
            return "C";
        } else if (param % 5 == 0) {
            return "B";
        } else if (param % 3 == 0) {
            return "A";
        } else {
            return String.valueOf(param);
        }
    }

    public void print(String type) {

        switch (type) {
            case "A":
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("A");
                    }
                });
                break;
            case "B":
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("B");
                    }
                });
                break;
            case "C":
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("C");
                    }
                });
                break;
            default:
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(type);
                    }
                });
                break;
        }
    }


    public static void main(String[] args) {
        Question1 question1 = new Question1();
        for(int i = 0;i<params.length;i++){
            question1.print(question1.calculate(params[i]));
        }
    }


}
