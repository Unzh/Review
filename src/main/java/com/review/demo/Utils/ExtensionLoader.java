package com.review.demo.Utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * 利用SPI 实现动态加载接口实现
 */
public abstract class ExtensionLoader {

    /**
     * 实现动态加载接口的实现
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Set<T> getExtension(Class<T> clazz) {
        Set<T> set = new HashSet<T>();
        Iterator<T> iterator = ServiceLoader.load(clazz).iterator();
        while (iterator.hasNext()) {
            set.add(iterator.next());
        }
        return set;
    }

    /**
     * 实现动态加载接口的实现
     * 并且提供默认实现
     * @param clazz
     * @param defaultImpl
     * @param <T>
     * @return
     * @throws IllegalStateException
     */
    public static <T> T getExtension(Class<T> clazz, T defaultImpl) throws IllegalStateException {
        Set<T> extension = getExtension(clazz);
        if (extension.isEmpty()) {
            return defaultImpl;
        }
        if (extension.size() > 1) {
            throw new IllegalStateException("implementer has more than 1,only 1 can be used");
        }
        return extension.iterator().next();
    }


}
