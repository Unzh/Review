package com.review.demo.single;

/**
 * Title : Singleton.java
 * Package : com.review.demo.single
 * Description : <p>
 * 单例模式
 * </p>
 * Create on : 2020/5/22
 *
 */
public class Singleton {

    private static volatile Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
