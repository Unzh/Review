package com.review.demo.single;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Title : FactorySingleton.java
 * Package : com.review.demo.single
 * Description : <p>容器模式实现单例模式</p>
 * Create on : 2020/5/22
 */
public class ContainerSingleton {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContainerSingleton.class);

    private ContainerSingleton() {
    }

    private static Map<Object, Object> ioc = new ConcurrentHashMap<>();

    public static Object getUniqueInstance(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object obj = null;
                try {
                    obj = Class.forName(className).newInstance();
                    ioc.put(className, obj);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return ioc.get(className);
    }
}
