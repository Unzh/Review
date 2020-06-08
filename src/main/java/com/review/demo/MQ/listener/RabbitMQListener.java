package com.review.demo.MQ.listener;

/**
 * Title : RabbitMQListener.java
 * Package : com.review.demo.MQ.listener
 * Description : RabbitMQ 监听器
 * Create on : 15:53
 */
public abstract class RabbitMQListener<T> {
    /**
     * 获取消息
     *
     * @param exchangeName
     * @param routingKey
     * @param queueName
     * @param message
     */
    public abstract boolean onMessage(String exchangeName, String routingKey, String queueName, T message);

}
