package com.review.demo.MQ.model;

import com.review.demo.MQ.listener.RabbitMQListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

/**
 * Title : RabbitMQConsumer.java
 * Package : com.review.demo.MQ.model
 * Description : RabbitMQ 消费者
 * Create on : 15:51
 *
 */
public class RabbitMQConsumer {
    private SimpleMessageListenerContainer container;

    private ConnectionFactory connectionFactory;

    private RabbitAdmin rabbitAdmin;

    private String queueName;

    private RabbitMQListener rabbitMQListener;

    public RabbitMQConsumer(ConnectionFactory connectionFactory, String queueName,
                            RabbitMQListener rabbitMQListener) {
        this.connectionFactory = connectionFactory;
        this.queueName = queueName;
        this.rabbitAdmin = new RabbitAdmin(connectionFactory);
        this.rabbitMQListener = rabbitMQListener;
    }

    /**
     * 开启监控
     */
    public void start() {
        container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //声明队列
        Queue queue = new Queue(queueName);
        queue.setAdminsThatShouldDeclare(rabbitAdmin);
        rabbitAdmin.declareQueue(queue);
        container.addQueueNames(queueName);
        //注册监听器
        /**if (rabbitMQListener != null) {
         container.setMessageListener(new SimpleRabbitMQListener(rabbitMQListener));
         }*/
        //MANUAL:监听器手动应答消息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.start();
    }

    /**
     * 停止监听
     */
    public void stop() {
        if (isRunning()) {
            container.stop();
        }
    }

    /**
     *监听是否运行
     * @return
     */
    public boolean isRunning() {
        return container != null && container.isRunning();
    }

}
