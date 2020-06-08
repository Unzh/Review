package com.review.demo.Utils;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

public class SpringUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static SpringUtil INSTANCE = new SpringUtil();

    public static SpringUtil getInstance() {
        return INSTANCE;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @param name  the name of the bean to retrieve
     * @param clazz type the bean must match.
     * @return an instance of the bean
     * @see org.springframework.beans.factory.BeanFactory#getBean(String, Class)
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getInstance().getApplicationContext().getBean(name, clazz);
    }


    public static Object getBean(String name) {
        return getInstance().getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getInstance().getApplicationContext().getBean(clazz);
    }

    public static <T> Collection<T> getBeanOfType(Class<T> clazz) {
        Map<String, T> beanMaps = getInstance().getApplicationContext().getBeansOfType(clazz);
        if (beanMaps != null) {
            return beanMaps.values();
        }
        return CollectionUtils.EMPTY_COLLECTION;
    }

    /**
     * @param name the name of the bean to query
     * @return whether a bean with the given name is present
     * @see org.springframework.beans.factory.BeanFactory#containsBean(String)
     */

    public static boolean containBean(String name) {
        return getInstance().getApplicationContext().containsBean(name);
    }

    /**
     * 获取HttpServletRequest
     *
     * @returnk
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取Session
     *
     * @returnk
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static String getContextPath() {
        return getSession().getServletContext().getRealPath("/");
    }

    /**
     * 等同 new 操作，但对象是被spring容器托管的
     *
     * @param beanName
     * @param clazz
     * @param argValues
     * @param propertyValues
     * @param isSingleton
     */
    public static synchronized void registerBeanDefinition(String beanName, Class<?> clazz, Object[] argValues,
                                                           Map<String, Object> propertyValues, boolean isSingleton) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) getInstance().getApplicationContext();
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        beanDefinitionBuilder.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
        beanDefinitionBuilder
                .setScope(isSingleton ? ConfigurableBeanFactory.SCOPE_SINGLETON : ConfigurableBeanFactory.SCOPE_PROTOTYPE);
        if (propertyValues != null && propertyValues.size() > 0) {
            for (Map.Entry<String, Object> entry : propertyValues.entrySet()) {
                beanDefinitionBuilder.addPropertyValue(entry.getKey(), entry.getValue());
            }
        }

        if (argValues != null && argValues.length > 0) {
            for (Object argValue : argValues) {
                beanDefinitionBuilder.addConstructorArgValue(argValue);
            }
        }
        beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }
}
