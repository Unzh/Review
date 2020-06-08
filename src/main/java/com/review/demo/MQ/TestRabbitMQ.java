package com.review.demo.MQ;

import com.review.demo.MQ.binder.RabbitMQBinder;
import com.review.demo.MQ.eunms.ExchangeType;
import com.review.demo.MQ.factory.RabbitMQFactory;
import com.review.demo.MQ.listener.RabbitMQListener;
import com.review.demo.MQ.model.RabbitMQConsumer;
import com.review.demo.MQ.model.RabbitMQSender;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Title : TestRabbitMQ.java
 * Package : com.review.demo.MQ
 * Description : RabbitMQ 测试类
 * Create on : 2020/3/20 9:32
 *
 */
public class TestRabbitMQ implements Serializable {


    private static final String QUEUE = "queue";

    private static final String EXCHANGE = "exchange";

    private static final String ROUTING_KEY = "routingKey";
    private static final long serialVersionUID = 458304522393629323L;


    public void main(String[] args) {
        CachingConnectionFactory factory = connectionFactory();
        //生产者
        final RabbitMQSender sender = RabbitMQFactory.createSender(factory, EXCHANGE, ROUTING_KEY);
        //消费者
        RabbitMQConsumer consumer = RabbitMQFactory.createConsumer(factory, QUEUE, new RabbitMQListener<String>() {
            @Override
            public boolean onMessage(String exchangeName, String routingKey, String queueName, String message) {
                return true;
            }
        });
        consumer.start();
        //关联关系
        RabbitMQBinder binder = RabbitMQFactory.createBinder(factory);
        binder.bind(EXCHANGE, ROUTING_KEY, QUEUE, ExchangeType.DIRECT);
        //发送消息
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            }
        }, 1000, 5000);
    }

    /**
     * 获得连接
     */
    private static CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("127.0.0.1:5672");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }
}
