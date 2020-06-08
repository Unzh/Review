package com.review.demo.MQ.binder;

import com.review.demo.MQ.eunms.ExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;


/**
 * Title : RabbitMQBinder.java
 * Package : com.review.demo.MQ.binder
 * Description : <p>
 * RabbitMQ 绑定
 * </p>
 * Create on : 2020/4/15
 *
 */
public class RabbitMQBinder {

    private RabbitAdmin rabbitAdmin;

    public RabbitMQBinder(ConnectionFactory connectionFactory) {
        rabbitAdmin = new RabbitAdmin(connectionFactory);
    }

    public void bind(String exchangeName, String routingKey, String queueName, ExchangeType exchangeType) {
        //声明队列
        Queue queue = new Queue(queueName);
        queue.setAdminsThatShouldDeclare(rabbitAdmin);
        //声明交换器  绑定交换器和队列
        switch (exchangeType) {
            case FANOUT:
                FanoutExchange fanoutExchange = new FanoutExchange(exchangeName);
                rabbitAdmin.declareExchange(fanoutExchange);
                rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(fanoutExchange));
            case DIRECT:
                DirectExchange directExchange = new DirectExchange(exchangeName);
                rabbitAdmin.declareExchange(directExchange);
                rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(routingKey));
            case TOPIC:
                TopicExchange topicExchange = new TopicExchange(exchangeName);
                rabbitAdmin.declareExchange(topicExchange);
                rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange).with(routingKey));
            default:
                break;
        }
    }
}
