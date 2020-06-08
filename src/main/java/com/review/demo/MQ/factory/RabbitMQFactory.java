package com.review.demo.MQ.factory;

import com.review.demo.MQ.binder.RabbitMQBinder;
import com.review.demo.MQ.eunms.ExchangeType;
import com.review.demo.MQ.listener.RabbitMQListener;
import com.review.demo.MQ.model.RabbitMQConsumer;
import com.review.demo.MQ.model.RabbitMQSender;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

/**
 * Title : RabbitMQFactory.java
 * Package : com.review.demo.MQ.factory
 * Description :RabbitMQ工厂
 * Create on : 14:31
 *
 */
public class RabbitMQFactory {

    private static ConnectionFactory connectionFactory;

    public RabbitMQFactory(ConnectionFactory connectionFactory) {
        RabbitMQFactory.connectionFactory = connectionFactory;
    }

    /**
     * 创建消息发送者
     * 交换器类型Fanout
     *
     * @param exchangeName
     * @param queueName
     * @return
     */
    public static RabbitMQSender createFanoutSender(String exchangeName, String queueName) {
        return createSender(connectionFactory, exchangeName, null, queueName, ExchangeType.FANOUT);
    }

    /**
     * 创建消息发送者
     * 交换器类型Direct
     *
     * @param queueName
     * @return
     */
    public static RabbitMQSender createDirectSender(String queueName) {
        return createSender(connectionFactory, "", "", queueName, ExchangeType.DIRECT);
    }

    /**
     * 创建消息发送者
     * 交换器类型Topic
     *
     * @param exchangeName
     * @param routingKey
     * @param queueName
     * @return
     */
    public static RabbitMQSender createTopicSender(String exchangeName, String routingKey, String queueName) {
        return createSender(connectionFactory, exchangeName, routingKey, queueName, ExchangeType.TOPIC);
    }

    /**
     * 创建消息发送者
     *
     * @param factory
     * @param exchangeName
     * @param routingKey
     * @param queueName
     * @param exchangeType
     * @return
     */
    public static RabbitMQSender createSender(ConnectionFactory factory, String exchangeName, String routingKey,
                                              String queueName, ExchangeType exchangeType) {
        RabbitMQSender rabbitMQSender = new RabbitMQSender(factory, exchangeName, routingKey, queueName, exchangeType);
        rabbitMQSender.build();
        return rabbitMQSender;
    }
    /**
     * 创建消息发送者，未绑定交换器与队列的关系，需通过RabbitMQBinder绑定
     * @param connectionFactory
     * @param exchangeName
     * @param routingKey
     * @return
     */
    public static RabbitMQSender createSender(ConnectionFactory connectionFactory, String exchangeName, String routingKey) {
        return new RabbitMQSender(connectionFactory, exchangeName, routingKey);
    }

    public static RabbitMQConsumer createConsumer(String queueName, RabbitMQListener rabbitMQListener) {
        return createConsumer(connectionFactory, queueName, rabbitMQListener);
    }

    /**
     * 创建消息消费者
     *
     * @param connectionFactory
     * @param queueName
     * @param rabbitMQListener
     * @return
     */
    public static RabbitMQConsumer createConsumer(ConnectionFactory connectionFactory, String queueName,
                                                  RabbitMQListener rabbitMQListener) {
        return new RabbitMQConsumer(connectionFactory, queueName, rabbitMQListener);
    }

    /**
     * 创建绑定器
     *
     * @param connectionFactory
     * @return
     */
    public static RabbitMQBinder createBinder(ConnectionFactory connectionFactory) {
        return new RabbitMQBinder(connectionFactory);
    }

    public static RabbitMQBinder createBinder(){
        return new RabbitMQBinder(connectionFactory);
    }

}
