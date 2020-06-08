package com.review.demo.MQ.configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title : RabbitMQConfig.java
 * Package : com.review.demo.MQ.configuration
 * Description : RabbitMQ配置信息
 * Create on : 14:31
 *
 */
@Configuration
@ConditionalOnExpression("'${spring.rabbitmq.addresses:}'!=''")
public class RabbitMQConfig {

    //服务器地址
    @Value("${spring.rabbitmq.addresses:}")
    private String Host;
    //登录用户名
    @Value("${spring.rabbitmq.username:}")
    private String USERNAME;
    //登录密码
    @Value("${spring.rabbitmq.password:}")
    private String PASSWORD;
    //虚拟主机
    @Value("${spring.rabbitmq.virtual-host:}")
    private String vHost;

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri(Host);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(vHost);
        return factory;
    }


}
