package com.review.demo.MQ.model;

import com.review.demo.MQ.binder.RabbitMQBinder;
import com.review.demo.MQ.eunms.ExchangeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


/**
 * Title : RabbitMQSender.java
 * Package : com.review.demo.MQ.model
 * Description :RabbitMQ发送者
 * Create on : 14:57
 *
 */
public class RabbitMQSender {

    private ConnectionFactory connectionFactory;

    private RabbitTemplate rabbitTemplate;

    private String exchangeName;

    private String routingKey;

    private String queneName;

    private ExchangeType exchangeType;

    private RabbitMQBinder rabbitMQBinder;

    public RabbitMQSender(ConnectionFactory connectionFactory, String exchangeName, String routingKey) {
        this(connectionFactory, exchangeName, routingKey, null, null);
    }

    public RabbitMQSender(ConnectionFactory connectionFactory, String exchangeName, String routingKey, String queneName, ExchangeType exchangeType) {
        this.connectionFactory = connectionFactory;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.queneName = queneName;
        this.exchangeType = exchangeType;
        this.rabbitTemplate = new RabbitTemplate(connectionFactory);
        //mandatory true:如果exchange根据自身类型和消息routingKey找不到queue 则调用basic.return将消息返还给生产者
        //          false:则会将消息丢弃
        this.rabbitTemplate.setMandatory(true);
    }

    public void build() {
        if (StringUtils.isNotBlank(queneName) && exchangeType != null) {
            this.rabbitMQBinder = new RabbitMQBinder(connectionFactory);
            this.rabbitMQBinder.bind(exchangeName, routingKey, queneName, exchangeType);
        }
    }

    /**
     * 发送消息
     * @param routingKey
     * @param message
     */
    public void send(String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    /**
     * 发送消息
     * @param message
     */
    public void send(Object message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
